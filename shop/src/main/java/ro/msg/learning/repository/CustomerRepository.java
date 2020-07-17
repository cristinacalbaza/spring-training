package ro.msg.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByUsername(String username);

}
