package ro.msg.learning.service.util;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.dto.OrderDetailDto;
import ro.msg.learning.dto.StockDto;
import ro.msg.learning.model.Location;
import ro.msg.learning.repository.StockRepository;

import java.util.*;

@RequiredArgsConstructor
public class SingleLocationStrategy implements FindLocationStrategy {

    private final StockRepository stockRepository;

    @Override
    public List<StockDto> findLocations(List<OrderDetailDto> orderDetails) {
        List<StockDto> result = new ArrayList<>();
        Map<Integer, List<Location>> productListMap = new LinkedHashMap<>();
        orderDetails.forEach(orderDetailDto -> productListMap.put(orderDetailDto.getProductId(),
                stockRepository.findAvailableLocations(orderDetailDto.getProductId(), orderDetailDto.getQuantity())));

        List<Location> commonLocations = productListMap.entrySet().iterator().next().getValue();
        productListMap.forEach((integer, stocks) -> commonLocations.retainAll(stocks));
        if (!commonLocations.isEmpty()){
            orderDetails.forEach(orderDetailDto -> result.add(new StockDto(commonLocations.get(0), orderDetailDto.getProductId(), orderDetailDto.getQuantity())));
        }
        return result;
    }
}
