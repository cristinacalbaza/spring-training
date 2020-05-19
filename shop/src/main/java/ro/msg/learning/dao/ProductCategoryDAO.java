package ro.msg.learning.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.model.ProductCategory;

public interface ProductCategoryDAO extends JpaRepository<ProductCategory, Integer> {
}
