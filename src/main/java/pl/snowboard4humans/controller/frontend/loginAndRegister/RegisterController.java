package pl.snowboard4humans.controller.frontend.loginAndRegister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.snowboard4humans.constants.ConstantsFrontendPL;
import pl.snowboard4humans.model.Customer;
import pl.snowboard4humans.service.frontend.CustomerServices;

@Controller
public class RegisterController {

    private CustomerServices customerServices;

    @Autowired
    public RegisterController(CustomerServices customerServices) {
        this.customerServices = customerServices;
    }

    @GetMapping(value = ConstantsFrontendPL.REGISTER_FORM_PAGE)
    public String getRegisterPage(Model model) {
        return customerServices.registerFormCustomer(model);
    }

    @PostMapping(value = ConstantsFrontendPL.REGISTER_FORM_PAGE)
    public String postRegisterPage(@ModelAttribute Customer customerRegisterData,
                                   Model model) {
        return customerServices.registerCustomer(customerRegisterData, model);
    }

}
