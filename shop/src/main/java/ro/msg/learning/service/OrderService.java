package ro.msg.learning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.dto.OrderDto;
import ro.msg.learning.dto.StockDto;
import ro.msg.learning.repository.OrderRepository;
import ro.msg.learning.service.util.FindLocationStrategy;
import ro.msg.learning.service.util.FindLocationStrategyFactory;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final FindLocationStrategy findLocationStrategy;

    public OrderDto create(OrderDto order){
        List<StockDto> stockDtos = findLocationStrategy.findLocations(order.getProducts());
        return order;
    }
}
