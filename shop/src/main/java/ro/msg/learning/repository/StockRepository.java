package ro.msg.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.msg.learning.model.Stock;
import ro.msg.learning.model.StockId;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, StockId> {

    @Query("SELECT s FROM Stock s WHERE s.product.id = :productId AND s.quantity >= :productQuantity")
    List<Stock> findAvailableLocations(@Param("productId") int productId, @Param("productQuantity") int productQuantity);

    List<Stock> findByLocationId(int locationId);
}
