package ro.msg.learning.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.msg.learning.model.OrderDetail;
import ro.msg.learning.model.OrderDetailId;

@Getter @Setter @AllArgsConstructor
public class OrderDetailDto {

    private int productId;
    private int quantity;

    public OrderDetailDto(OrderDetail orderDetail){
        productId = orderDetail.getId().getProductId();
        quantity = orderDetail.getQuantity();
    }

    public OrderDetail toEntity(){
        return new OrderDetail(new OrderDetailId(0, productId), null, null, quantity);
    }
}
