package pl.snowboard4humans.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.snowboard4humans.constants.ConstantsAdminENG;
import pl.snowboard4humans.constants.ConstantsPL;
import pl.snowboard4humans.model.Customer;
import pl.snowboard4humans.repository.CustomerRepo;
import pl.snowboard4humans.service.SuperService;
import pl.snowboard4humans.utils.Utils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerAdminServices extends SuperService {
    private CustomerRepo customerRepo;

    @Autowired
    public CustomerAdminServices(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public String getCategories(Model model) {
        String message = ConstantsAdminENG.LIST_OF_CUSTOMER_ADMIN;

        List<Customer> customerList = customerRepo.findAll();

        // if customer list is empty then message change
        if (Utils.isEmpty(customerList)) {
            message = ConstantsAdminENG.LACK_OF_CUSTOMER_IN_DB;
        }

        return getRequestDispatcherWithDefaultMessage(model,
                message,
                ConstantsAdminENG.CUSTOMER_LIST_OBJECT,
                customerList,
                ConstantsAdminENG.CUSTOMER_LIST_URL);
    }

    public String getAddNewCustomerScreen(Model model) {
        return getRequestDispatcherWithDefaultMessage(model,
                ConstantsAdminENG.CREATE_MODE_FILL_FIELDS_CUSTOMER_ADMIN,
                ConstantsAdminENG.CUSTOMER_OBJECT,
                new Customer(),
                ConstantsAdminENG.CUSTOMER_CREATE_OR_UPDATE_URL);
    }

    public String addOrUpdateCustomer(Model model,
                                      Customer customer) {
        String message;

        Integer customerId = customer.getId();
        String email = customer.getEmail();
        List<Customer> customersByEmail = customerRepo.findCustomersByEmail(email);
        if (customerId == null) {
            message = ConstantsAdminENG.NEW_CUSTOMER_WAS_NOT_CREATED + email + ConstantsAdminENG.CUSTOMER_NAME_ALREADY_EXIST_IN_DB;
            String returnValue = checkIfUserAlreadyExistInDbByEmail(model, customersByEmail, message);
            if (returnValue != null) {
                return returnValue;
            }
            customer.setRegisterDate(new Date());
        } else {
            message = ConstantsAdminENG.CUSTOMER_WAS_NOT_UPDATED + email + ConstantsAdminENG.CUSTOMER_NAME_ALREADY_EXIST_IN_DB;

            String returnValueByEmail = checkIfUserAlreadyExistInDbByEmail(model, customersByEmail, message);
            if (returnValueByEmail != null) {
                return returnValueByEmail;
            }

            Customer customerById = customerRepo.findCustomerById(customerId);
            String returnValueById = checkIfUserAlreadyExistInDbById(model,
                    customersByEmail.size() > 0 ? customersByEmail.get(0) : null,
                    message,
                    customerById);
            if (returnValueById != null) {
                return returnValueById;
            }

            if (customer.getPassword().length() == 0) {
                customer.setPassword(customerById.getPassword());
            }
            customer.setRegisterDate(customerById.getRegisterDate());
        }

        Customer customerSaved = customerRepo.save(customer);

        List<Customer> customerList = customerRepo.findAll();
        if (customerId == null && customerSaved != null && customerSaved.getId() != null) {
            message = ConstantsAdminENG.NEW_CUSTOMER_WAS_CREATED;
        } else if (customerId != null && customerSaved != null && customerSaved.getId() != null) {
            message = ConstantsAdminENG.CUSTOMER_WAS_UPDATED;
        } else if (customerId == null && customerSaved == null) {
            message = ConstantsAdminENG.NEW_CUSTOMER_WAS_NOT_CREATED;
        } else {
            message = ConstantsAdminENG.CUSTOMER_WAS_NOT_UPDATED;
        }

        return getRequestDispatcherWithDefaultMessage(model,
                message,
                ConstantsAdminENG.CUSTOMER_LIST_OBJECT,
                customerList,
                ConstantsAdminENG.CUSTOMER_LIST_URL);
    }

    private String checkIfUserAlreadyExistInDbById(Model model,
                                                   Customer customerByEmail,
                                                   String message,
                                                   Customer customerById) {
        if (customerByEmail != null && !customerByEmail.getId().equals(customerById.getId())) {
            List<Customer> customerList = customerRepo.findAll();
            return getRequestDispatcherWithDefaultMessage(model,
                    message,
                    ConstantsAdminENG.CUSTOMER_LIST_OBJECT,
                    customerList,
                    ConstantsAdminENG.CUSTOMER_LIST_URL);
        }
        return null;
    }

    private String checkIfUserAlreadyExistInDbByEmail(Model model,
                                                      List<Customer> customersByEmail,
                                                      String message) {
        if (customersByEmail.size() > 0) {
            List<Customer> customerList = customerRepo.findAll();
            return getRequestDispatcherWithDefaultMessage(model,
                    message,
                    ConstantsAdminENG.CUSTOMER_LIST_OBJECT,
                    customerList,
                    ConstantsAdminENG.CUSTOMER_LIST_URL);
        }
        return null;
    }

    public String getEditCustomerScreen(Model model,
                                        int customerId) {
        Optional<Customer> customerOptional = customerRepo.findById(customerId);
        Customer customer = customerOptional.orElseGet(Customer::new);

        return getRequestDispatcherWithDefaultMessage(model,
                ConstantsPL.EMPTY_MESSAGE,
                ConstantsAdminENG.CUSTOMER_OBJECT,
                customer,
                ConstantsAdminENG.CUSTOMER_CREATE_OR_UPDATE_URL);
    }

    public String getViewCustomerScreen(Model model,
                                        int customerId) {
        Optional<Customer> customerOptional = customerRepo.findById(customerId);
        Customer customer = customerOptional.orElseGet(Customer::new);

        return getRequestDispatcherWithDefaultMessage(model,
                ConstantsPL.EMPTY_MESSAGE,
                ConstantsAdminENG.CUSTOMER_OBJECT,
                customer,
                ConstantsAdminENG.CUSTOMER_VIEW_URL);
    }
}
