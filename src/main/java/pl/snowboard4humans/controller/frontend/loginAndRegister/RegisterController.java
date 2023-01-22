package pl.snowboard4humans.controller.frontend.loginAndRegister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.snowboard4humans.constants.ConstantsFrontendPL;
import pl.snowboard4humans.constants.ConstantsPL;
import pl.snowboard4humans.controller.frontend.SuperController;
import pl.snowboard4humans.dto.MsgAndListDto;
import pl.snowboard4humans.model.Customer;
import pl.snowboard4humans.service.frontend.CustomerService;

@Controller
public class RegisterController extends SuperController {

  private final CustomerService customerService;

  @Autowired
  public RegisterController(final CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping(value = ConstantsFrontendPL.REGISTER_FORM_PAGE)
  public String getRegisterPage(final Model model) {

    return getRequestDispatcherWithDefaultMessage(model,
        ConstantsPL.NULL,
        ConstantsFrontendPL.REGISTER_CUSTOMER_OBJECT,
        new Customer(),
        ConstantsFrontendPL.REGISTER_FORM_PAGE);
  }

  @PostMapping(value = ConstantsFrontendPL.REGISTER_FORM_PAGE)
  public String postRegisterPage(final Model model,
                                 @ModelAttribute final Customer customerRegisterData) {
    final MsgAndListDto<Customer> cmald = customerService.registerCustomer(customerRegisterData);

    if (cmald.isAnonymous()) {
      return getRequestDispatcherWithDefaultMessage(model,
          cmald.getMessage(),
          ConstantsFrontendPL.REGISTER_CUSTOMER_OBJECT,
          new Customer(),
          cmald.getUrl());
    }

    return getRequestDispatcherWithDefaultMessage(model,
        cmald.getMessage(),
        ConstantsFrontendPL.LOGIN_CUSTOMER_OBJECT,
        new Customer(),
        cmald.getUrl());
  }

}
