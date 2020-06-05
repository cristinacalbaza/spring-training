package ro.msg.learning.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.msg.learning.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @AllArgsConstructor
public class OrderDto {

    private LocalDateTime createdAt;
    private Address address;
    private List<OrderDetailDto> products;

    public OrderDto(Order order){
        createdAt = order.getCreatedAt();
        address = order.getAddress();
        products = new ArrayList<>();
        List<OrderDetail> orderDetails = order.getOrderDetailList();
        orderDetails.forEach(orderDetail -> products.add(new OrderDetailDto(orderDetail)));
    }

    public Order toEntity(){
        List<OrderDetail> orderDetailList = new ArrayList<>();
        products.forEach(orderDetailDto -> orderDetailList.add(orderDetailDto.toEntity()));
        return new Order(0, new Location(), new Customer(), createdAt, address, orderDetailList);
    }
}
