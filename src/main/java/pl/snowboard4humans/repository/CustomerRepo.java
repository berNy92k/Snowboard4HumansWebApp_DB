package pl.snowboard4humans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.snowboard4humans.model.Customer;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    List<Customer> findCustomersByEmail(String email);

    Customer findCustomerByEmail(String email);

    Customer findCustomerById(Integer id);

}
