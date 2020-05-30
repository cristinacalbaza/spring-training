package ro.msg.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
