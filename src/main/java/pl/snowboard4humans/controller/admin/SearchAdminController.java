package pl.snowboard4humans.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.snowboard4humans.service.admin.EquipmentAdminService;

@Controller
@RequestMapping(value = "/admin/search")
public class SearchAdminController {

    private EquipmentAdminService equipmentAdminService;

    @Autowired
    public SearchAdminController(EquipmentAdminService equipmentAdminService) {
        this.equipmentAdminService = equipmentAdminService;
    }

    @GetMapping
    public String getEquipmentBy(Model model,
                                 @RequestParam(value = "searchBy") String searchBy) {

        return equipmentAdminService.searchEquipmentList(model, searchBy);
    }

}
