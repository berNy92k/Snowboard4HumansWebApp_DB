package pl.snowboard4humans.service.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.snowboard4humans.constants.ConstantsFrontendPL;
import pl.snowboard4humans.constants.ConstantsPL;
import pl.snowboard4humans.constants.ConstantsUtils;
import pl.snowboard4humans.model.Equipment;
import pl.snowboard4humans.repository.EquipmentRepo;
import pl.snowboard4humans.service.SuperService;

import java.util.LinkedList;
import java.util.List;

@Service
public class HomeServices extends SuperService {
    private EquipmentRepo equipmentRepo;

    @Autowired
    public HomeServices(EquipmentRepo equipmentRepo) {
        this.equipmentRepo = equipmentRepo;
    }

    public String getShortListOdEquipments(Model model) {
        List<Equipment> allEquipments = equipmentRepo.findAll();

        List<Equipment> top4equipments = new LinkedList<>();
        if (allEquipments.size() <= 4) {
            top4equipments = allEquipments;
        } else {
            setRandomEquipmentsToList(allEquipments, top4equipments, true);
            if (top4equipments.size() < 4) {
                setRandomEquipmentsToList(allEquipments, top4equipments, false);
            }
        }

        return getRequestDispatcherWithOutDefaultMessageAsBoolean(model,
                ConstantsPL.MESSAGE_EMPTY,
                ConstantsUtils.FALSE,
                ConstantsFrontendPL.EQUIPMENT_SHORT_LIST,
                top4equipments,
                ConstantsUtils.INDEX_HTML);
    }

    private List<Equipment> setRandomEquipmentsToList(List<Equipment> allEquipments,
                                                      List<Equipment> top4equipments,
                                                      boolean getEvenNumbers) {
        int counter = 1;
        for (Equipment equipment : allEquipments) {
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

        return top4equipments;
    }
}
