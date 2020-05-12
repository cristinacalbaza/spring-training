package ro.msg.learning.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @EqualsAndHashCode
public class OrderDetailId implements Serializable {

    @Column(name = "order_id", nullable = false)
    private int orderId;

    @Column(name = "product_id", nullable = false)
    private int productId;
}
