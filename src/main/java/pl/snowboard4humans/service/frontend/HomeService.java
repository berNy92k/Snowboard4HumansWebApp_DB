package pl.snowboard4humans.service.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.snowboard4humans.model.Equipment;
import pl.snowboard4humans.repository.EquipmentRepo;
import pl.snowboard4humans.service.SuperService;

import java.util.List;

@Service
public class HomeService extends SuperService {

  private final EquipmentRepo equipmentRepo;

  @Autowired
  public HomeService(final EquipmentRepo equipmentRepo) {
    this.equipmentRepo = equipmentRepo;
  }

  public List<Equipment> getShortListOdEquipments() {
    return getTop4Equipments(equipmentRepo.findAll());
  }

}
