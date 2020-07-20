package ro.msg.learning.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import ro.msg.learning.model.Address;
import ro.msg.learning.model.Location;

@Builder
@AllArgsConstructor
public class LocationDto {
    private String street;
    private String city;
    private String country;

    public LocationDto(Address address){
        this.street = address.getStreetAddress();
        this.city = address.getCity();
        this.country = address.getCountry();
    }

    public LocationDto(Location location){
        this.street = location.getAddress().getStreetAddress();
        this.city = location.getAddress().getCity();
        this.country = location.getAddress().getCountry();
    }

    @Override
    public String toString() {
        return "" + this.street + ", " + this.city + ", " + this.country + "";
    }
}
