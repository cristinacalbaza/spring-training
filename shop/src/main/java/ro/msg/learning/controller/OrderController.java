package ro.msg.learning.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.dto.OrderDto;
import ro.msg.learning.service.OrderService;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private final OrderService orderService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public OrderDto create(@RequestBody OrderDto orderDto){
        LocalDateTime now = java.time.LocalDateTime.now();
        orderDto.setCreatedAt(now);
        return orderService.create(orderDto);
    }

    @GetMapping(produces = "application/json")
    public List<OrderDto> getAll(){
        return orderService.getAll();
    }
}
