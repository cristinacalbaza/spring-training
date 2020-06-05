package ro.msg.learning.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.msg.learning.model.Location;
import ro.msg.learning.model.Stock;

@Getter @Setter @AllArgsConstructor
public class StockDto {

    private Location location;
    private int productId;
    private int quantity;

    public StockDto(Stock stock){
        location = stock.getLocation();
        productId = stock.getProduct().getId();
        quantity = stock.getQuantity();
    }

}
