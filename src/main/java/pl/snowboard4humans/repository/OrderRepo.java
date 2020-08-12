package pl.snowboard4humans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.snowboard4humans.model.Order;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Integer> {

    List<Order> findOrdersByCustomerId(Integer customerId);

}
