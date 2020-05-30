package ro.msg.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}
