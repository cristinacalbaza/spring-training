package ro.msg.learning.dto;

import jdk.jfr.Category;
import lombok.*;
import ro.msg.learning.model.Product;
import ro.msg.learning.model.ProductCategory;
import ro.msg.learning.model.Supplier;

import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor
public class ProductDto {

    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private double weight;
    private String categoryName;
    private String categoryDescription;
    private String supplierName;
    private String imageURL;

    public ProductDto(Product product){
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        weight = product.getWeight();
        categoryName = product.getCategory().getName();
        categoryDescription = product.getCategory().getDescription();
        supplierName = product.getSupplier().getName();
        imageURL = product.getImageURL();
    }

    public Product toEntity(){

        return new Product(id, name, description, price, weight,
                new ProductCategory(0, categoryName, categoryDescription),
                new Supplier(0, supplierName), imageURL);
    }
}
