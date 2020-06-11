package ro.msg.learning.service.util;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.dto.OrderDetailDto;
import ro.msg.learning.dto.StockDto;
import ro.msg.learning.model.Location;
import ro.msg.learning.model.Stock;
import ro.msg.learning.repository.ProductRepository;
import ro.msg.learning.repository.StockRepository;
import ro.msg.learning.service.exception.OutOfStockException;

import java.util.*;

@RequiredArgsConstructor
public class SingleLocationStrategy implements FindLocationStrategy {

    private final StockRepository stockRepository;
    private final ProductRepository productRepository;

    @Override
    public List<StockDto> findLocations(List<OrderDetailDto> orderDetails) {
        List<StockDto> result = new ArrayList<>();
        Map<Integer, List<Stock>> productListMap = new LinkedHashMap<>();
        orderDetails.forEach(orderDetailDto -> productListMap.put(orderDetailDto.getProductId(),
                stockRepository.findAvailableLocations(orderDetailDto.getProductId(), orderDetailDto.getQuantity())));

        Map<Integer, List<Location>> availableLocations = new LinkedHashMap<>();
        productListMap.forEach((integer, stocks) -> availableLocations.put(integer, getLocations(stocks)));

        List<Location> commonLocations = availableLocations.entrySet().iterator().next().getValue();
        availableLocations.forEach((integer, stocks) -> commonLocations.retainAll(stocks));
        if (commonLocations.isEmpty()){
            throw new OutOfStockException();
        }
        orderDetails.forEach(orderDetailDto -> result.add(new StockDto(commonLocations.get(0), productRepository.getOne(orderDetailDto.getProductId()), orderDetailDto.getQuantity())));
        return result;
    }

    private List<Location> getLocations(List<Stock> stocks){
        List<Location> locations = new ArrayList<>();
        stocks.forEach(stock -> locations.add(stock.getLocation()));
        return locations;
    }
}
