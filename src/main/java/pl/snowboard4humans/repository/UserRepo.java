package pl.snowboard4humans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.snowboard4humans.model.User;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Integer> {

    List<User> findAllByEmail(String email);

    List<User> findAllByEmailAndPassword(String email, String password);

}
