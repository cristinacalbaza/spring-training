package ro.msg.learning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ro.msg.learning.security.UserDetail;
import ro.msg.learning.model.Customer;
import ro.msg.learning.repository.CustomerRepository;
import ro.msg.learning.service.exception.UsernameNotFoundException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Override
    public UserDetail loadUserByUsername(String username){
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findByUsername(username));
        if (customer.isEmpty())
            throw new UsernameNotFoundException(username);
        return new UserDetail(customer.get());
    }
}
