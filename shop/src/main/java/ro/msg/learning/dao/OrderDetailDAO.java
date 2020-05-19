package ro.msg.learning.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.model.OrderDetail;
import ro.msg.learning.model.OrderDetailId;

import java.util.List;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, OrderDetailId> {

    List<OrderDetail> findByOrderId(int orderId);
}
