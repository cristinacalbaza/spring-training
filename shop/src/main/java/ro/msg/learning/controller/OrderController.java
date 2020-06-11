package ro.msg.learning.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.dto.OrderDto;
import ro.msg.learning.service.OrderService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public OrderDto create(@RequestBody OrderDto orderDto){
        return orderService.create(orderDto);
    }
}
