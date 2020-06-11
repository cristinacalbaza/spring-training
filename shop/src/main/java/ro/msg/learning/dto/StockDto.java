package ro.msg.learning.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ro.msg.learning.model.Location;
import ro.msg.learning.model.Product;
import ro.msg.learning.model.Stock;
import ro.msg.learning.model.StockId;

@Getter @Setter @AllArgsConstructor
public class StockDto {

    private Location location;
    private Product product;
    private int quantity;

    public StockDto(Stock stock){
        location = stock.getLocation();
        product = stock.getProduct();
        quantity = stock.getQuantity();
    }

    public Stock toEntity(){
        return new Stock(new StockId(product.getId(), location.getId()), product, location, quantity);
    }

}
