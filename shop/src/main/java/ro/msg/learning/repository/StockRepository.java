package ro.msg.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.model.Stock;
import ro.msg.learning.model.StockId;

public interface StockRepository extends JpaRepository<Stock, StockId> {
}
