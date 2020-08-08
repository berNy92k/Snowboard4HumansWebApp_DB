package pl.snowboard4humans.controller.frontend.manufacturer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.snowboard4humans.service.frontend.ManufacturerServices;

@Controller
@RequestMapping(value = "/homepage/manufacturer")
public class ManufacturerController {

    private ManufacturerServices manufacturerServices;

    @Autowired
    public ManufacturerController(ManufacturerServices manufacturerServices) {
        this.manufacturerServices = manufacturerServices;
    }

    @GetMapping
    public String getManufacturer(Model model) {
        return manufacturerServices.manufacturerList(model);
    }

}
