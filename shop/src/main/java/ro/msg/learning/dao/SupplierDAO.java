package ro.msg.learning.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.model.Supplier;

public interface SupplierDAO extends JpaRepository<Supplier, Integer> {
}
