package ro.msg.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.model.Revenue;

import java.time.LocalDate;
import java.util.List;

public interface RevenueRepository extends JpaRepository<Revenue, Integer> {

    List<Revenue> findByDate(LocalDate date);
}
