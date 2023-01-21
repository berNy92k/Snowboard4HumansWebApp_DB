package pl.snowboard4humans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.snowboard4humans.model.Review;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Review, Integer> {

  List<Review> findReviewsByEquipmentId(Integer equipmentId);

  Review findReviewById(Integer equipmentId);

}
