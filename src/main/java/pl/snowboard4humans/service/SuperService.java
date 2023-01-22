package pl.snowboard4humans.service;

import pl.snowboard4humans.model.Equipment;

import java.util.LinkedList;
import java.util.List;

public class SuperService {

  protected List<Equipment> getTop4Equipments(final List<Equipment> allEquipments) {
    List<Equipment> top4equipments = new LinkedList<>();
    if (allEquipments.size() <= 4) {
      top4equipments = allEquipments;
    } else {
      setRandomEquipmentsToList(allEquipments, top4equipments, true);
      if (top4equipments.size() < 4) {
        setRandomEquipmentsToList(allEquipments, top4equipments, false);
      }
    }
    return top4equipments;
  }

  private void setRandomEquipmentsToList(final List<Equipment> allEquipments,
                                         final List<Equipment> top4equipments,
                                         final boolean getEvenNumbers) {
    int counter = 1;
    for (final Equipment equipment : allEquipments) {
      if (getEvenNumbers) {
        if (counter % 2 == 0) {
          top4equipments.add(equipment);
        }
      } else {
        if (counter % 2 != 0) {
          top4equipments.add(equipment);
        }
      }

      counter++;
      if (top4equipments.size() >= 4) {
        break;
      }
    }
  }

}
