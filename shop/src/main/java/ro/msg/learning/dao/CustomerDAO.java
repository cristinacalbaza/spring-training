package ro.msg.learning.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.model.Customer;

public interface CustomerDAO extends JpaRepository<Customer, Integer> {

}
