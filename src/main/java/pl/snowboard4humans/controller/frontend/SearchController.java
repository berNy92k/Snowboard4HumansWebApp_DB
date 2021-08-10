package pl.snowboard4humans.controller.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.snowboard4humans.service.frontend.EquipmentService;

@Controller
@RequestMapping(value = "/homepage/search")
public class SearchController {

    private final EquipmentService equipmentService;

    @Autowired
    public SearchController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping
    public String getEquipmentBy(Model model,
                                 @RequestParam(value = "searchBy") String searchBy) {

        return equipmentService.searchEquipmentList(model, searchBy);
    }

}
