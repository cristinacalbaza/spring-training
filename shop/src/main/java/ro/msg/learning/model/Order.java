package ro.msg.learning.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Order")
@Table(name = "orders", schema = "shop")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @EqualsAndHashCode
public class Order {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "shipped_from_location_id")
    private Location shippedFrom;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Embedded
    private Address address;


}
