package pl.snowboard4humans.controller.frontend.loginAndRegister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.snowboard4humans.constants.ConstantsFrontendPL;
import pl.snowboard4humans.model.Customer;
import pl.snowboard4humans.service.frontend.CustomerServices;

@Controller
@RequestMapping(value = "")
public class LoginController {

    private CustomerServices customerServices;

    @Autowired
    public LoginController(CustomerServices customerServices) {
        this.customerServices = customerServices;
    }

    @GetMapping(value = ConstantsFrontendPL.LOGIN_FORM_PAGE)
    public String getLoginPage(Model model) {
        return customerServices.loginFormCustomer(model);
    }

    @PostMapping(value = ConstantsFrontendPL.LOGIN_FORM_PAGE)
    public String postLoginPage(@ModelAttribute Customer customerLoginData,
                                Model model) {
        return customerServices.loginAsCustomer(customerLoginData, model);
    }
}
