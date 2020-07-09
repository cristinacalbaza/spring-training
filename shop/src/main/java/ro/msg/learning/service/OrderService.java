package ro.msg.learning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.dto.OrderDto;
import ro.msg.learning.dto.StockDto;
import ro.msg.learning.model.*;
import ro.msg.learning.repository.CustomerRepository;
import ro.msg.learning.repository.OrderDetailRepository;
import ro.msg.learning.repository.OrderRepository;
import ro.msg.learning.repository.StockRepository;
import ro.msg.learning.service.exception.OutOfStockException;
import ro.msg.learning.service.util.strategy.FindLocationStrategy;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final StockService stockService;
    private final StockRepository stockRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final FindLocationStrategy findLocationStrategy;

    public OrderDto create(OrderDto order) {
        Customer customer = customerRepository.getOne(1);
        // run the strategy
        List<StockDto> stockDtos = findLocationStrategy.findLocations(order.getProducts());
        // substract goods
        stockDtos.forEach(stockDto -> { stockDto.setQuantity(stockRepository.getOne(stockDto.toEntity().getId()).getQuantity() - stockDto.getQuantity());
                                        stockService.update(stockDto);
                                      } );
        // save order
        Order createdOrder = order.toEntity();
        createdOrder.setShippedFrom(stockDtos.get(0).getLocation());
        createdOrder.setCustomer(customer);
        Order savedOrder = orderRepository.save(createdOrder);

        // save Order Details
        List<OrderDetail> orderDetails = getOrderDetails(stockDtos, savedOrder);
        orderDetails.forEach(orderDetailRepository::save);
        return new OrderDto(savedOrder);
    }

    private List<OrderDetail> getOrderDetails(List<StockDto> stockDtos, Order order){
        List<OrderDetail> orderDetails = new ArrayList<>();
        stockDtos.forEach(stockDto -> orderDetails.add(new OrderDetail(new OrderDetailId(order.getId(),
                                                                        stockDto.getProduct().getId()),
                                                                        order,
                                                                        stockDto.getProduct(),
                                                                        stockDto.getQuantity())));
        return orderDetails;
    }

    public void deleteAll(){
        orderRepository.deleteAll();
    }

}
