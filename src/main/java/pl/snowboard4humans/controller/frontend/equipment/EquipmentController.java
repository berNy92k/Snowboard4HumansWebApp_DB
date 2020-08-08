package pl.snowboard4humans.controller.frontend.equipment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.snowboard4humans.service.frontend.EquipmentServices;

@Controller
@RequestMapping(value = "/homepage")
public class EquipmentController {

    private EquipmentServices equipmentServices;

    @Autowired
    public EquipmentController(EquipmentServices equipmentServices) {
        this.equipmentServices = equipmentServices;
    }

    @GetMapping(value = "/equipment")
    public String getEquipment(Model model,
                               @RequestParam String eqm,
                               @RequestParam String sex) {

        return equipmentServices.equipmentList(model, eqm, sex);
    }

    @PostMapping(value = "/equipment")
    public String postEquipment(Model model,
                                @RequestParam String eqm,
                                @RequestParam String sex) {

        return equipmentServices.equipmentList(model, eqm, sex);
    }

    @GetMapping(value = "/viewEquipment")
    public String viewEquipment(Model model,
                                @RequestParam Integer id) {

        return equipmentServices.viewEquipment(model, id);
    }
}
