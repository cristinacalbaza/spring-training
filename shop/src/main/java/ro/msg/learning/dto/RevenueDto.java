package ro.msg.learning.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.msg.learning.model.Location;
import ro.msg.learning.model.Revenue;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class RevenueDto {

    private Location location;
    private LocalDate date;
    private BigDecimal sum;

    public RevenueDto(Revenue revenue){
        this.location = revenue.getLocation();
        this.date = revenue.getDate();
        this.sum = revenue.getSum();
    }

    public Revenue toEntity(){
        return new Revenue(0, this.location, this.date, this.sum);
    }
}
