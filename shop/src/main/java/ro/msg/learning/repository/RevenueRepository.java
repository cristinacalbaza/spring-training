package ro.msg.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.model.Revenue;

public interface RevenueRepository extends JpaRepository<Revenue, Integer> {
}
