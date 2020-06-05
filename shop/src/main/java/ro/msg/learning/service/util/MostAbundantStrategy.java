package ro.msg.learning.service.util;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.dto.OrderDetailDto;
import ro.msg.learning.dto.StockDto;
import ro.msg.learning.model.Location;
import ro.msg.learning.repository.StockRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class MostAbundantStrategy implements FindLocationStrategy {

    private final StockRepository stockRepository;

    @Override
    public List<StockDto> findLocations(List<OrderDetailDto> orderDetails) {
        List<StockDto> result = new ArrayList<>();
        Map<Integer, List<Location>> productListMap = new LinkedHashMap<>();
        orderDetails.forEach(orderDetailDto -> productListMap.put(orderDetailDto.getProductId(),
                stockRepository.findAvailableLocations(orderDetailDto.getProductId(), orderDetailDto.getQuantity())));

        return null;
    }
}
