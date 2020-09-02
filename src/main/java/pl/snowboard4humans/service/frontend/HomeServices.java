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

import java.util.List;

@Service
public class HomeServices extends SuperService {
    private EquipmentRepo equipmentRepo;

    @Autowired
    public HomeServices(EquipmentRepo equipmentRepo) {
        this.equipmentRepo = equipmentRepo;
    }

    public String getShortListOdEquipments(Model model) {
        List<Equipment> top4equipments = getTop4Equipments(equipmentRepo.findAll());

        return getRequestDispatcherWithOutDefaultMessageAsBoolean(model,
                ConstantsPL.MESSAGE_EMPTY,
                ConstantsUtils.FALSE,
                ConstantsFrontendPL.EQUIPMENT_SHORT_LIST,
                top4equipments,
                ConstantsUtils.INDEX_HTML);
    }

}
