package pl.snowboard4humans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.snowboard4humans.model.Manufacturer;

import java.util.List;

public interface ManufacturerRepo extends JpaRepository<Manufacturer, Integer> {

    List<Manufacturer> findAllByManufacturerName(String manufacturerName);

}
