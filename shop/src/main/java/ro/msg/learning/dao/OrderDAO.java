package ro.msg.learning.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.model.Order;

public interface OrderDAO extends JpaRepository<Order, Integer> {
}
