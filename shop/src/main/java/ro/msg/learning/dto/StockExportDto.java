package ro.msg.learning.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import ro.msg.learning.model.Stock;

import java.io.Serializable;

@Getter @Setter @AllArgsConstructor
@NoArgsConstructor
@ToString @EqualsAndHashCode
@JsonPropertyOrder({"productId", "locationId", "quantity"})
public class StockExportDto implements Serializable {

    @JsonProperty("ProductId")
    private int productId;
    @JsonProperty("LocationId")
    private int locationId;
    @JsonProperty("Quantity")
    private int quantity;

    public StockExportDto(Stock stock){
        productId = stock.getId().getProductId();
        locationId = stock.getId().getLocationId();
        quantity = stock.getQuantity();

    }

}
