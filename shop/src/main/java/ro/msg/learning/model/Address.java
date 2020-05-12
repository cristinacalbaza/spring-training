package ro.msg.learning.model;

import lombok.*;

import javax.persistence.*;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @EqualsAndHashCode
public class Address {

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "county")
    private String county;

    @Column(name = "street_address")
    private String streetAddress;
}
