package ro.msg.learning.model;

import lombok.*;

import javax.persistence.*;

@Entity(name = "Location")
@Table(name = "location", schema = "shop")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @EqualsAndHashCode
public class Location {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Embedded
    private Address address;

}
