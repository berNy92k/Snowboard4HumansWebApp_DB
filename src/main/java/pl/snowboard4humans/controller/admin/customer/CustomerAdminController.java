package pl.snowboard4humans.controller.admin.customer;

import org.springframework.beans.factory.annotation.Autowired;
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
import pl.snowboard4humans.model.Customer;
import pl.snowboard4humans.service.admin.CustomerAdminService;

@Controller
@RequestMapping(value = "/admin/customer")
public class CustomerAdminController extends SuperController {

  private final CustomerAdminService customerAdminService;

  @Autowired
  public CustomerAdminController(final CustomerAdminService customerAdminService) {
    this.customerAdminService = customerAdminService;
  }

  @GetMapping
  public String getCustomers(final Model model) {
    final MsgAndListDto<Customer> customers = customerAdminService.getCustomers();

    return getRequestDispatcherWithDefaultMessage(model,
        customers.getMessage(),
        ConstantsAdminENG.CUSTOMER_LIST_OBJECT,
        customers.getListOfElements(),
        ConstantsAdminENG.CUSTOMER_LIST_URL);
  }

  @PostMapping
  public String addCustomer(final Model model,
                            @ModelAttribute final Customer customer) {
    final MsgAndListDto<Customer> customers = customerAdminService.addOrUpdateCustomer(customer);

    return getRequestDispatcherWithDefaultMessage(model,
        customers.getMessage(),
        ConstantsAdminENG.CUSTOMER_LIST_OBJECT,
        customers.getListOfElements(),
        ConstantsAdminENG.CUSTOMER_LIST_URL);
  }

  @GetMapping(value = "customer_create")
  public String addNewCategoryScreen(final Model model) {

    return getRequestDispatcherWithDefaultMessage(model,
        ConstantsAdminENG.CREATE_MODE_FILL_FIELDS_CUSTOMER_ADMIN,
        ConstantsAdminENG.CUSTOMER_OBJECT,
        new Customer(),
        ConstantsAdminENG.CUSTOMER_CREATE_OR_UPDATE_URL);
  }

  @GetMapping(value = "editCustomer")
  public String editCategoryScreen(final Model model,
                                   @RequestParam(value = "id") final int customerId) {
    final MsgAndListDto<Customer> customers = customerAdminService.getEditCustomerScreen(customerId);

    return getRequestDispatcherWithDefaultMessage(model,
        customers.getMessage(),
        ConstantsAdminENG.CUSTOMER_OBJECT,
        customers.getListOfElements(),
        ConstantsAdminENG.CUSTOMER_CREATE_OR_UPDATE_URL);
  }

  @GetMapping(value = "viewCustomer")
  public String viewCategoryScreen(final Model model,
                                   @RequestParam(value = "id") final int customerId) {
    final MsgAndListDto<Customer> customers = customerAdminService.getViewCustomerScreen(customerId);

    return getRequestDispatcherWithDefaultMessage(model,
        customers.getMessage(),
        ConstantsAdminENG.CUSTOMER_OBJECT,
        customers.getListOfElements(),
        ConstantsAdminENG.CUSTOMER_VIEW_URL);
  }

  @GetMapping(value = "deleteCustomer")
  public String deleteCustomer(final Model model,
                               @RequestParam(name = "id") final int customerId) {
    final MsgAndListDto<Customer> customers = customerAdminService.deleteCustomer(customerId);

    return getRequestDispatcherWithDefaultMessage(model,
        customers.getMessage(),
        ConstantsAdminENG.CUSTOMER_LIST_OBJECT,
        customers.getListOfElements(),
        ConstantsAdminENG.CUSTOMER_LIST_URL);
  }

}
