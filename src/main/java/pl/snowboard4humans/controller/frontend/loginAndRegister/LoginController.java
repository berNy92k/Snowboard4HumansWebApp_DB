package pl.snowboard4humans.controller.frontend.loginAndRegister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.snowboard4humans.constants.ConstantsFrontendPL;
import pl.snowboard4humans.constants.ConstantsPL;
import pl.snowboard4humans.controller.frontend.SuperController;
import pl.snowboard4humans.dto.MsgAndListDto;
import pl.snowboard4humans.model.Customer;
import pl.snowboard4humans.service.frontend.CustomerService;

@Controller
@RequestMapping(value = "")
public class LoginController extends SuperController {

    private final CustomerService customerService;

    @Autowired
    public LoginController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = ConstantsFrontendPL.LOGIN_FORM_PAGE)
    public String getLoginPage(final Model model) {

        return getRequestDispatcherWithDefaultMessage(model,
                ConstantsPL.NULL,
                ConstantsFrontendPL.LOGIN_CUSTOMER_OBJECT,
                new Customer(),
                ConstantsFrontendPL.LOGIN_FORM_PAGE);
    }

    @PostMapping(value = ConstantsFrontendPL.LOGIN_FORM_PAGE)
    public String postLoginPage(final Model model,
                                @ModelAttribute final Customer customerLoginData) {
        final MsgAndListDto<Object> msgAndListDto = customerService.loginAsCustomer(customerLoginData);

        if (msgAndListDto.isAnonymous()) {
            return getRequestDispatcherWithDefaultMessage(model,
                    msgAndListDto.getMessage(),
                    ConstantsFrontendPL.LOGIN_CUSTOMER_OBJECT,
                    msgAndListDto.getListOfElements().get(0),
                    msgAndListDto.getUrl());
        } else {
            return getRequestDispatcherWithDefaultMessage(model,
                    msgAndListDto.getMessage(),
                    ConstantsFrontendPL.EQUIPMENT_SHORT_LIST,
                    msgAndListDto.getListOfElements().get(0),
                    msgAndListDto.getUrl());
        }
    }
}
