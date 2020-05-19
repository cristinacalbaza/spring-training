package ro.msg.learning.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.model.Revenue;

public interface RevenueDAO extends JpaRepository<Revenue, Integer> {
}
