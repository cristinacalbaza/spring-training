package ro.msg.learning.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.msg.learning.model.ProductCategory;

@Getter
@Setter
@NoArgsConstructor
public class ProductCategoryDto {

    private int id;
    private String categoryName;

    public ProductCategoryDto(ProductCategory productCategory){
        this.id = productCategory.getId();
        this.categoryName = productCategory.getName();
    }

    public ProductCategory toEntity(){
        return new ProductCategory(this.id, this.categoryName, "");
    }
}
