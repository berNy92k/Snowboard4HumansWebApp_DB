package pl.snowboard4humans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.snowboard4humans.model.Equipment;

import java.util.List;

public interface EquipmentRepo extends JpaRepository<Equipment, Integer> {

    List<Equipment> findAllByName(String name);

    List<Equipment> findEquipmentByCategoryId(int category);

    List<Equipment> findEquipmentByCategoryIdAndSex(Integer category,
                                                    String sex);

    List<Equipment> findEquipmentByNameLikeOrLongDescriptionLikeOrShortDescriptionLike(String name,
                                                                                       String longDescription,
                                                                                       String shortDescription);
}
