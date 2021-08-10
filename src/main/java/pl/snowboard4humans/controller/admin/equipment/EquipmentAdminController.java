package pl.snowboard4humans.controller.admin.equipment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.snowboard4humans.model.Equipment;
import pl.snowboard4humans.service.admin.EquipmentAdminService;

@Controller
@RequestMapping(value = "/admin/equipment")
public class EquipmentAdminController {

    private EquipmentAdminService equipmentAdminService;

    @Autowired
    public EquipmentAdminController(EquipmentAdminService equipmentAdminService) {
        this.equipmentAdminService = equipmentAdminService;
    }

    @GetMapping
    public String getEquipmentType(@RequestParam String type) {
        return equipmentAdminService.equipmentTypeUrl(type);
    }

    @GetMapping(value = "details")
    public String getEquipment(Model model,
                               @RequestParam String eqm,
                               @RequestParam String sex) {

        return equipmentAdminService.equipmentList(model, eqm, sex);
    }

    @PostMapping(value = "details",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String postEquipment(Model model,
                                @ModelAttribute Equipment equipment) {

        return equipmentAdminService.addOrUpdateEquipment(model, equipment);
    }

    @GetMapping(value = "precreateNewEquipment")
    public String preCreateNewEquipment(Model model,
                                        @RequestParam String eqm,
                                        @RequestParam String sex) {

        return equipmentAdminService.preCreateNewEquipment(model, eqm, sex);
    }

    @GetMapping(value = "editEquipment")
    public String editEquipment(Model model,
                                @RequestParam(value = "id") int equipmentId) {

        return equipmentAdminService.editEquipment(model, equipmentId);
    }

    @GetMapping(value = "deleteEquipment")
    public String deleteEquipment(@RequestParam(name = "id") int equipmentId,
                                 Model model) {
        return equipmentAdminService.deleteEquipment(model, equipmentId);
    }

    @GetMapping(value = "details/viewEquipment")
    public String viewEquipment(Model model,
                                @RequestParam int id) {

        return equipmentAdminService.viewEquipment(model, id);
    }
}
