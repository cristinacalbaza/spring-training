package ro.msg.learning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.model.Customer;
import ro.msg.learning.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer save(Customer customer){
        customer.setPassword(customer.getPassword());
        return customerRepository.save(customer);
    }

    public void deleteAll(){
        customerRepository.deleteAll();
    }

    public Customer get(String username){
        return customerRepository.findByUsername(username);
    }
}
