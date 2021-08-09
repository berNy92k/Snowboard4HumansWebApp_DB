package pl.snowboard4humans.service.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.snowboard4humans.constants.ConstantsFrontendPL;
import pl.snowboard4humans.constants.ConstantsPL;
import pl.snowboard4humans.constants.ConstantsUtils;
import pl.snowboard4humans.model.Customer;
import pl.snowboard4humans.model.Equipment;
import pl.snowboard4humans.repository.CustomerRepo;
import pl.snowboard4humans.repository.EquipmentRepo;
import pl.snowboard4humans.service.SuperService;
import pl.snowboard4humans.utils.Utils;

import java.util.Date;
import java.util.List;

@Service
public class CustomerServices extends SuperService {

    private final CustomerRepo customerRepo;
    private final EquipmentRepo equipmentRepo;

    @Autowired
    public CustomerServices(CustomerRepo customerRepo,
                            EquipmentRepo equipmentRepo) {
        this.customerRepo = customerRepo;
        this.equipmentRepo = equipmentRepo;
    }

    public String loginFormCustomer(Model model) {
        return getRequestDispatcherWithDefaultMessage(model,
                ConstantsPL.NULL,
                ConstantsFrontendPL.LOGIN_CUSTOMER_OBJECT,
                new Customer(),
                ConstantsFrontendPL.LOGIN_FORM_PAGE);
    }

    public String loginAsCustomer(Customer customerLoginData,
                                  Model model) {
        String email = customerLoginData.getEmail();
        String password = customerLoginData.getPassword();

        List<Customer> customersFoundByEmail = customerRepo.findCustomersByEmail(email);
        if (!Utils.isEmpty(customersFoundByEmail)) {
            Customer customer = customersFoundByEmail.get(0);
            if (customer.getPassword().equals(password)) {
                List<Equipment> top4equipments = getTop4Equipments(equipmentRepo.findAll());

                return getRequestDispatcherWithDefaultMessage(model,
                        ConstantsFrontendPL.LOGIN_SUCCESS,
                        ConstantsFrontendPL.EQUIPMENT_SHORT_LIST,
                        top4equipments,
                        ConstantsUtils.INDEX_HTML);
            } else {
                return getRequestDispatcherWithDefaultMessage(model,
                        ConstantsFrontendPL.LOGIN_FAILED,
                        ConstantsFrontendPL.LOGIN_CUSTOMER_OBJECT,
                        new Customer(),
                        ConstantsFrontendPL.LOGIN_FORM_PAGE);
            }
        } else {
            return getRequestDispatcherWithDefaultMessage(model,
                    ConstantsFrontendPL.LOGIN_FAILED,
                    ConstantsFrontendPL.LOGIN_CUSTOMER_OBJECT,
                    new Customer(),
                    ConstantsFrontendPL.LOGIN_FORM_PAGE);
        }
    }

    public String logoutCustomer(Model model) {
        List<Equipment> top4equipments = getTop4Equipments(equipmentRepo.findAll());

        return getRequestDispatcherWithDefaultMessage(model,
                ConstantsFrontendPL.LOGIN_SUCCESS,
                ConstantsFrontendPL.EQUIPMENT_SHORT_LIST,
                top4equipments,
                ConstantsFrontendPL.HOMEPAGE_URL);
    }

    public String registerFormCustomer(Model model) {
        return getRequestDispatcherWithDefaultMessage(model,
                ConstantsPL.NULL,
                ConstantsFrontendPL.REGISTER_CUSTOMER_OBJECT,
                new Customer(),
                ConstantsFrontendPL.REGISTER_FORM_PAGE);
    }

    public String registerCustomer(Customer customerRegisterData,
                                   Model model) {
        List<Customer> customersFoundByEmail = customerRepo.findCustomersByEmail(customerRegisterData.getEmail());
        if (!Utils.isEmpty(customersFoundByEmail)) {
            return getRequestDispatcherWithDefaultMessage(model,
                    ConstantsFrontendPL.REGISTER_FAILED,
                    ConstantsFrontendPL.REGISTER_CUSTOMER_OBJECT,
                    new Customer(),
                    ConstantsFrontendPL.REGISTER_FORM_PAGE);
        } else {
            Customer newCustomer = getNewCustomer(customerRegisterData, ConstantsUtils.FALSE, null);
            customerRepo.save(newCustomer);

            return getRequestDispatcherWithDefaultMessage(model,
                    ConstantsFrontendPL.REGISTER_SUCCESS,
                    ConstantsFrontendPL.LOGIN_CUSTOMER_OBJECT,
                    new Customer(),
                    ConstantsFrontendPL.LOGIN_FORM_PAGE);
        }
    }

    public String myAccountViewCustomer(Model model) {
        return myAccountViewCustomer(null, model);
    }

    public String myAccountViewCustomer(String message,
                                        Model model) {
        //TODO dodac zamowienia

        return getRequestDispatcherWithDefaultMessage(model,
                message,
                ConstantsFrontendPL.LOGIN_CUSTOMER_OBJECT,
                new Customer(),
                ConstantsFrontendPL.MY_ACCOUNT_VIEW_URL);
    }

    public String myAccountEditCustomer() {
        return ConstantsFrontendPL.MY_ACCOUNT_CREATE_URL;
    }

    public void myAccountUpdateCustomer(Customer customerUpdateData,
                                        Model model) {
        boolean isMoreCategoriesInDatabase = false;

        Customer customerFoundById = customerRepo.getOne(customerUpdateData.getId());

        List<Customer> customers = customerRepo.findCustomersByEmail(customerUpdateData.getEmail());
        Customer customerFoundByEmail = null;
        if (!Utils.isEmpty(customers) && customers.size() < 2) {
            customerFoundByEmail = customers.get(0);
        } else if (customers.size() > 1) {
            isMoreCategoriesInDatabase = true;
        }

        if (isMoreCategoriesInDatabase) {
            String email = customerUpdateData.getEmail();
            String message = ConstantsFrontendPL.MY_ACCOUNT_CUSTOMER_WAS_NOT_UPDATED + email + ConstantsFrontendPL.MY_ACCOUNT_CUSTOMER_NAME_ALREADY_EXIST_IN_DB;
            myAccountViewCustomer(message, model);
        } else if (customerFoundByEmail != null && !customerFoundByEmail.getId().equals(customerFoundById.getId())) {
            String email = customerUpdateData.getEmail();
            String message = ConstantsFrontendPL.MY_ACCOUNT_CUSTOMER_WAS_NOT_UPDATED + email + ConstantsFrontendPL.MY_ACCOUNT_CUSTOMER_NAME_ALREADY_EXIST_IN_DB;
            myAccountViewCustomer(message, model);
        } else {
            Customer newCustomer = getNewCustomer(customerUpdateData, ConstantsUtils.TRUE, customerFoundById);
            Customer customer = customerRepo.save(newCustomer);

            model.addAttribute(ConstantsFrontendPL.LOGGED_CUSTOMER, customer);

            myAccountViewCustomer(ConstantsFrontendPL.MY_ACCOUNT_CUSTOMER_WAS_UPDATED, model);
        }
    }

    private Customer getNewCustomer(Customer customerData,
                                    boolean isUpdate,
                                    Customer customerDataById) {

        Customer newCustomer = new Customer();
        if (isUpdate) {
            newCustomer.setId(customerData.getId());
        }
        newCustomer.setEmail(customerData.getEmail());
        if (isUpdate && customerData.getPassword().length() == 0) {
            newCustomer.setPassword(customerDataById.getPassword());
        } else {
            newCustomer.setPassword(customerData.getPassword());
        }
        newCustomer.setFirstName(customerData.getFirstName());
        newCustomer.setLastName(customerData.getLastName());
        newCustomer.setStreet(customerData.getStreet());
        newCustomer.setHomeNumber(customerData.getHomeNumber());
        newCustomer.setCity(customerData.getCity());
        newCustomer.setZipCode(customerData.getZipCode());
        newCustomer.setCountry(customerData.getCountry());
        newCustomer.setPhone(customerData.getPhone());
        if (isUpdate) {
            newCustomer.setRegisterDate(customerDataById.getRegisterDate());
        } else {
            newCustomer.setRegisterDate(new Date());
        }
        return newCustomer;
    }

}
