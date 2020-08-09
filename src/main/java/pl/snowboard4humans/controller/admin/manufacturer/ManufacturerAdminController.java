package pl.snowboard4humans.controller.admin.manufacturer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.snowboard4humans.model.Manufacturer;
import pl.snowboard4humans.service.admin.ManufacturerAdminServices;

@Controller
@RequestMapping(value = "/admin/manufacturer")
public class ManufacturerAdminController {

    private ManufacturerAdminServices manufacturerAdminServices;

    @Autowired
    public ManufacturerAdminController(ManufacturerAdminServices manufacturerAdminServices) {
        this.manufacturerAdminServices = manufacturerAdminServices;
    }

    @GetMapping
    public String getManufacturers(Model model) {
        return manufacturerAdminServices.getManufacturers(model);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addOrUpdateManufacturer(Model model,
                                          @ModelAttribute Manufacturer manufacturer) {
        return manufacturerAdminServices.addOrUpdateManufacturer(model, manufacturer);
    }

    @GetMapping(value = "edit")
    public String getEditManufactureScreen(@RequestParam(name = "id") int manufacturerId,
                                           Model model) {
        return manufacturerAdminServices.getEditManufactureScreen(manufacturerId, model);
    }

    @GetMapping(value = "create")
    public String getAddNewManufacturerScreen(Model model) {
        return manufacturerAdminServices.getAddNewManufacturerScreen(model);
    }

}
