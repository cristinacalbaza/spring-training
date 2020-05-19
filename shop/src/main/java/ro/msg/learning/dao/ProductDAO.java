package ro.msg.learning.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.model.Product;

public interface ProductDAO extends JpaRepository<Product, Integer> {
}
