package ro.msg.learning.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "Product")
@Table(name = "product", schema = "shop")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @EqualsAndHashCode
public class Product {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "weight")
    private double weight;

    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private ProductCategory category;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Column(name = "image_url")
    private String imageURL;
}
