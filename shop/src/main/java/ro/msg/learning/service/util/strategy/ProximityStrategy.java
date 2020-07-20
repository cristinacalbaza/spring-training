package ro.msg.learning.service.util.strategy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import ro.msg.learning.dto.LocationDto;
import ro.msg.learning.dto.OrderDetailDto;
import ro.msg.learning.dto.StockDto;
import ro.msg.learning.model.Address;
import ro.msg.learning.model.Location;
import ro.msg.learning.model.Product;
import ro.msg.learning.model.Stock;
import ro.msg.learning.repository.LocationRepository;
import ro.msg.learning.repository.ProductRepository;
import ro.msg.learning.repository.StockRepository;
import ro.msg.learning.service.exception.ProductNotFoundException;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class ProximityStrategy implements FindLocationStrategy {

    @Autowired
    private final StockRepository stockRepository;

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final LocationRepository locationRepository;

    @Autowired
    private final ObjectMapper objectMapper;

    private final String apiUrl;

    @Override
    public List<StockDto> findLocations(List<OrderDetailDto> orderDetails, Address orderAddress) {
        List<StockDto> result = new ArrayList<>();
        Map<Integer, List<Stock>> availableLocations = new LinkedHashMap<>();
        Map<Integer, Stock> finalLocations = new LinkedHashMap<>();

        orderDetails.forEach(orderDetailDto -> { Optional<Product> product = productRepository.findById(orderDetailDto.getProductId());
            if (product.isEmpty())
                throw new ProductNotFoundException(orderDetailDto.getProductId());
        });

        orderDetails.forEach(orderDetailDto -> {
            availableLocations.put(orderDetailDto.getProductId(), stockRepository.findAvailableLocations(orderDetailDto.getProductId(), orderDetailDto.getQuantity()));
            result.add(new StockDto(new Location(), productRepository.getOne(orderDetailDto.getProductId()), orderDetailDto.getQuantity()));
        } );

        availableLocations.forEach((integer, stocks) -> {
            try {
                Map<Stock, Double> distances = getDistances(orderAddress, stocks);
                finalLocations.put(integer, distances.keySet().iterator().next());
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        });

        result.forEach(stockDto -> stockDto.setLocation(finalLocations.get(stockDto.getProduct().getId()).getLocation()));
        return result;
    }

    private Map<Stock, Double> getDistances(Address orderAddress, List<Stock> availableLocations) throws JSONException, IOException {
        Map<Stock, Double> distances = new LinkedHashMap<>();
        JSONArray locations = new JSONArray();
        RestTemplate restTemplate = new RestTemplate();
        JSONObject searchObject = new JSONObject();
        JSONObject searchOptions = new JSONObject();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        locations.put(LocationDto.builder().street(orderAddress.getStreetAddress()).city(orderAddress.getCity()).country(orderAddress.getCountry()));
        availableLocations.forEach(stock -> locations.put(LocationDto.builder().street(stock.getLocation().getAddress().getStreetAddress())
                                                                               .city(stock.getLocation().getAddress().getCity())
                                                                               .country(stock.getLocation().getAddress().getCountry())));

        searchObject.put("locations", locations);
        searchObject.put("locations", locations);
        searchOptions.put("manyToOne", true);
        searchOptions.put("unit", "k");
        searchObject.put("options", searchOptions);

        HttpEntity<String> request = new HttpEntity<>(searchObject.toString(), headers);
        String response = restTemplate.postForObject(apiUrl, request, String.class);
        JsonNode node = null;
        List<Double> distanceDataFromAPICall = new ArrayList<>();
        if (response != null) {
            node = objectMapper.readTree(response);

            ObjectReader reader = objectMapper.readerFor(new TypeReference<List<String>>() {
            });
            List<String> apiCallStringResponse = reader.readValue(node.get("distance"));

            apiCallStringResponse.forEach(s -> distanceDataFromAPICall.add(Double.parseDouble(s)));
        }

        AtomicInteger i = new AtomicInteger(1);
        availableLocations.forEach(stock -> { distances.put(stock, distanceDataFromAPICall.get(i.get()));
                                              i.getAndIncrement(); });

        // sort the values
        Map<Stock, Double> sorted = new LinkedHashMap<>();
        distances.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(stockDoubleEntry -> sorted.put(stockDoubleEntry.getKey(), stockDoubleEntry.getValue()));

        return sorted;
    }

}
