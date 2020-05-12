package ro.msg.learning.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "Revenue")
@Table(name = "revenue", schema = "shop")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @EqualsAndHashCode
public class Revenue {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "sum", nullable = false)
    private BigDecimal sum;
}
