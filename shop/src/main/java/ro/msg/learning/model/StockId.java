package ro.msg.learning.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @EqualsAndHashCode
public class StockId implements Serializable {

    @Column(name = "product_id", nullable = false)
    private int productId;

    @Column(name = "location_id", nullable = false)
    private int locationId;
}
