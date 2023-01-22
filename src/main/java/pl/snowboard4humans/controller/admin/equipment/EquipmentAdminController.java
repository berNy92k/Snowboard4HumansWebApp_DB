package pl.snowboard4humans.controller.admin.equipment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.snowboard4humans.model.Equipment;
import pl.snowboard4humans.service.admin.EquipmentAdminService;

@Controller
@RequestMapping(value = "/admin/equipment")
public class EquipmentAdminController {

  private final EquipmentAdminService equipmentAdminService;

  @Autowired
  public EquipmentAdminController(final EquipmentAdminService equipmentAdminService) {
    this.equipmentAdminService = equipmentAdminService;
  }

  @GetMapping
  public String getEquipmentType(@RequestParam final String type) {
    return equipmentAdminService.equipmentTypeUrl(type);
  }

  @GetMapping(value = "details")
  public String getEquipment(final Model model,
                             @RequestParam final String eqm,
                             @RequestParam final String sex) {

    return equipmentAdminService.equipmentList(model, eqm, sex);
  }

  @PostMapping(value = "details",
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public String postEquipment(final Model model,
                              @ModelAttribute final Equipment equipment) {

    return equipmentAdminService.addOrUpdateEquipment(model, equipment);
  }

  @GetMapping(value = "precreateNewEquipment")
  public String preCreateNewEquipment(final Model model,
                                      @RequestParam final String eqm,
                                      @RequestParam final String sex) {

    return equipmentAdminService.preCreateNewEquipment(model, eqm, sex);
  }

  @GetMapping(value = "editEquipment")
  public String editEquipment(final Model model,
                              @RequestParam(value = "id") final int equipmentId) {

    return equipmentAdminService.editEquipment(model, equipmentId);
  }

  @GetMapping(value = "deleteEquipment")
  public String deleteEquipment(final Model model,
                                @RequestParam(name = "id") final int equipmentId) {

    return equipmentAdminService.deleteEquipment(model, equipmentId);
  }

  @GetMapping(value = "details/viewEquipment")
  public String viewEquipment(final Model model,
                              @RequestParam final int id) {

    return equipmentAdminService.viewEquipment(model, id);
  }
}
