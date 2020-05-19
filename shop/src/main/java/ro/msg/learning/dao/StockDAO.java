package ro.msg.learning.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.model.Stock;
import ro.msg.learning.model.StockId;

public interface StockDAO extends JpaRepository<Stock, StockId> {
}
