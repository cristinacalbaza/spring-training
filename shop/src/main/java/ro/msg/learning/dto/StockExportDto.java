package ro.msg.learning.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import ro.msg.learning.model.Stock;

@Getter @Setter @AllArgsConstructor
@JsonPropertyOrder({"productId", "locationId", "quantity"})
public class StockExportDto {

    private int productId;
    private int locationId;
    private int quantity;

    public StockExportDto(Stock stock){
        productId = stock.getId().getProductId();
        locationId = stock.getId().getLocationId();
        quantity = stock.getQuantity();

    }

}
