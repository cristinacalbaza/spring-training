package ro.msg.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
