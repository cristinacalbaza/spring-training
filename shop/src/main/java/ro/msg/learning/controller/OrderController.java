package ro.msg.learning.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.dto.OrderDetailDto;
import ro.msg.learning.dto.OrderDto;
import ro.msg.learning.model.Address;
import ro.msg.learning.service.OrderService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<OrderDto> create(){
        List<OrderDetailDto> products = new ArrayList<>();
        products.add(new OrderDetailDto(1,70));
        products.add(new OrderDetailDto(2,25));
        products.add(new OrderDetailDto(3,9));
        OrderDto orderDto = new OrderDto(java.time.LocalDateTime.now(), new Address("Romania", "Cluj-Napoca", "Cluj", "Str. Dorobantilor, nr. 186"), products);
        return ResponseEntity.ok(orderService.create(orderDto));
    }
}
