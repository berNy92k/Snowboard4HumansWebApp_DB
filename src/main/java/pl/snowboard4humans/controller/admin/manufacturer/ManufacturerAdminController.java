package pl.snowboard4humans.controller.admin.manufacturer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.snowboard4humans.constants.ConstantsAdminENG;
import pl.snowboard4humans.controller.frontend.SuperController;
import pl.snowboard4humans.dto.MsgAndListDto;
import pl.snowboard4humans.model.Manufacturer;
import pl.snowboard4humans.service.admin.ManufacturerAdminService;

@Controller
@RequestMapping(value = "/admin/manufacturer")
public class ManufacturerAdminController extends SuperController {

  private final ManufacturerAdminService manufacturerAdminService;

  @Autowired
  public ManufacturerAdminController(final ManufacturerAdminService manufacturerAdminService) {
    this.manufacturerAdminService = manufacturerAdminService;
  }

  @GetMapping
  public String getManufacturers(final Model model) {
    final MsgAndListDto<Manufacturer> manufacturers = manufacturerAdminService.getManufacturers();

    return getRequestDispatcherWithDefaultMessage(model,
        manufacturers.getMessage(),
        ConstantsAdminENG.MANUFACTURER_LIST_OBJECT,
        manufacturers.getListOfElements(),
        ConstantsAdminENG.MANUFACTURER_LIST_URL);
  }

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public String addOrUpdateManufacturer(final Model model,
                                        @ModelAttribute final Manufacturer manufacturer) {
    final MsgAndListDto<Manufacturer> manufacturers = manufacturerAdminService.addOrUpdateManufacturer(manufacturer);

    return getRequestDispatcherWithDefaultMessage(model,
        manufacturers.getMessage(),
        ConstantsAdminENG.MANUFACTURER_LIST_OBJECT,
        manufacturers.getListOfElements(),
        ConstantsAdminENG.MANUFACTURER_LIST_URL);
  }

  @GetMapping(value = "edit")
  public String getEditManufactureScreen(@RequestParam(name = "id") final int manufacturerId,
                                         final Model model) {
    final MsgAndListDto<Manufacturer> manufacturers = manufacturerAdminService.getEditManufactureScreen(manufacturerId);

    return getRequestDispatcherWithDefaultMessage(model,
        manufacturers.getMessage(),
        ConstantsAdminENG.MANUFACTURER_OBJECT,
        manufacturers.getListOfElements(),
        ConstantsAdminENG.MANUFACTURER_CREATE_OR_UPDATE_URL);
  }

  @GetMapping(value = "create")
  public String getAddNewManufacturerScreen(final Model model) {

    return getRequestDispatcherWithDefaultMessage(model,
        ConstantsAdminENG.CREATE_MODE_FILL_FIELDS_MANUFACTURER_ADMIN,
        ConstantsAdminENG.MANUFACTURER_OBJECT,
        new Manufacturer(),
        ConstantsAdminENG.MANUFACTURER_CREATE_OR_UPDATE_URL);
  }

  @GetMapping(value = "deleteManufacturer")
  public String deleteManufacturer(final Model model,
                                   @RequestParam(name = "id") final int manufacturerId) {
    final MsgAndListDto<Manufacturer> manufacturer = manufacturerAdminService.deleteManufacturer(manufacturerId);

    return getRequestDispatcherWithDefaultMessage(model,
        manufacturer.getMessage(),
        ConstantsAdminENG.MANUFACTURER_LIST_OBJECT,
        manufacturer.getListOfElements(),
        ConstantsAdminENG.MANUFACTURER_LIST_URL);
  }

}
