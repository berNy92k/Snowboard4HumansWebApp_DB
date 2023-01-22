package pl.snowboard4humans.controller.frontend.manufacturer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.snowboard4humans.constants.ConstantsFrontendPL;
import pl.snowboard4humans.controller.frontend.SuperController;
import pl.snowboard4humans.dto.MsgAndListDto;
import pl.snowboard4humans.model.Manufacturer;
import pl.snowboard4humans.service.frontend.ManufacturerService;

@Controller
@RequestMapping(value = "/homepage/manufacturer")
public class ManufacturerController extends SuperController {

  private final ManufacturerService manufacturerService;

  @Autowired
  public ManufacturerController(final ManufacturerService manufacturerService) {
    this.manufacturerService = manufacturerService;
  }

  @GetMapping
  public String getManufacturer(final Model model) {
    final MsgAndListDto<Manufacturer> manufacturer = manufacturerService.manufacturerList();

    return getRequestDispatcherWithDefaultMessage(model,
        manufacturer.getMessage(),
        ConstantsFrontendPL.MANUFACTURER_LIST_OBJECT,
        manufacturer.getListOfElements(),
        ConstantsFrontendPL.MANUFACTURER_LIST_URL);
  }

}
