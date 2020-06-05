package ro.msg.learning.service.util;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.dto.OrderDetailDto;
import ro.msg.learning.dto.StockDto;
import ro.msg.learning.model.Stock;
import ro.msg.learning.repository.StockRepository;

import java.util.*;

@RequiredArgsConstructor
public class MostAbundantStrategy implements FindLocationStrategy {

    private final StockRepository stockRepository;

    @Override
    public List<StockDto> findLocations(List<OrderDetailDto> orderDetails) {
        List<StockDto> result = new ArrayList<>();
        Map<Integer, List<Stock>> availableLocations = new LinkedHashMap<>();
        orderDetails.forEach(orderDetailDto -> availableLocations.put(orderDetailDto.getProductId(),
                stockRepository.findAvailableLocations(orderDetailDto.getProductId(), orderDetailDto.getQuantity())));

        availableLocations.forEach((integer, stocks) -> result.add(new StockDto(getMostAbundand(stocks))));
        return result;
    }

    private Stock getMostAbundand(List<Stock> stocks){
        Optional<Stock> stock = stocks.stream().max(Comparator.comparingInt(Stock::getQuantity));
        return stock.orElse(null);
    }
}
