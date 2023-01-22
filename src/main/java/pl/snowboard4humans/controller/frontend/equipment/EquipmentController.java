package pl.snowboard4humans.controller.frontend.equipment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.snowboard4humans.service.frontend.EquipmentService;

@Controller
@RequestMapping(value = "/homepage/equipment")
public class EquipmentController {

  private final EquipmentService equipmentService;

  @Autowired
  public EquipmentController(final EquipmentService equipmentService) {
    this.equipmentService = equipmentService;
  }

  @GetMapping
  public String getEquipment(final Model model,
                             @RequestParam final String eqm,
                             @RequestParam final String sex) {

    return equipmentService.equipmentList(model, eqm, sex);
  }

  @GetMapping(value = "/viewEquipment")
  public String viewEquipment(final Model model,
                              @RequestParam final Integer id) {

    return equipmentService.viewEquipment(model, id);
  }
}
