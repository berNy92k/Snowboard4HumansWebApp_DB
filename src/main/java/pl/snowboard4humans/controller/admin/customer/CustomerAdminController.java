package pl.snowboard4humans.controller.admin.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.snowboard4humans.model.Customer;
import pl.snowboard4humans.service.admin.CustomerAdminServices;

@Controller
@RequestMapping(value = "/admin/customer")
public class CustomerAdminController {

    private CustomerAdminServices customerAdminServices;

    @Autowired
    public CustomerAdminController(CustomerAdminServices customerAdminServices) {
        this.customerAdminServices = customerAdminServices;
    }

    @GetMapping
    public String getCustomers(Model model) {
        return customerAdminServices.getCategories(model);
    }

    @PostMapping
    public String addCustomer(Model model,
                              @ModelAttribute Customer customer) {
        return customerAdminServices.addOrUpdateCustomer(model, customer);
    }

    @GetMapping(value = "customer_create")
    public String addNewCategoryScreen(Model model) {
        return customerAdminServices.getAddNewCustomerScreen(model);
    }

    @GetMapping(value = "editCustomer")
    public String editCategoryScreen(Model model,
                                     @RequestParam(value = "id") int customerId) {
        return customerAdminServices.getEditCustomerScreen(model, customerId);
    }

    @GetMapping(value = "viewCustomer")
    public String viewCategoryScreen(Model model,
                                     @RequestParam(value = "id") int customerId) {
        return customerAdminServices.getViewCustomerScreen(model, customerId);
    }


}
