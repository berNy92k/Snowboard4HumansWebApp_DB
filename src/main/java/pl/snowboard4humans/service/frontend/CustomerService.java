package pl.snowboard4humans.service.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.snowboard4humans.constants.ConstantsFrontendPL;
import pl.snowboard4humans.constants.ConstantsUtils;
import pl.snowboard4humans.dto.MsgAndListDto;
import pl.snowboard4humans.model.Customer;
import pl.snowboard4humans.model.Equipment;
import pl.snowboard4humans.repository.CustomerRepo;
import pl.snowboard4humans.repository.EquipmentRepo;
import pl.snowboard4humans.service.SuperService;
import pl.snowboard4humans.utils.Utils;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class CustomerService extends SuperService {

  private final CustomerRepo customerRepo;
  private final EquipmentRepo equipmentRepo;

  @Autowired
  public CustomerService(final CustomerRepo customerRepo,
                         final EquipmentRepo equipmentRepo) {
    this.customerRepo = customerRepo;
    this.equipmentRepo = equipmentRepo;
  }

  public MsgAndListDto<Object> loginAsCustomer(final Customer customerLoginData) {
    final String email = customerLoginData.getEmail();
    final List<Customer> customersFoundByEmail = customerRepo.findCustomersByEmail(email);

    if (!Utils.isEmpty(customersFoundByEmail)) {
      final Customer customer = customersFoundByEmail.get(0);
      if (customer.getPassword().equals(customerLoginData.getPassword())) {
        final List<Equipment> top4equipments = getTop4Equipments(equipmentRepo.findAll());
        return new MsgAndListDto<>(ConstantsFrontendPL.LOGIN_SUCCESS, Collections.singletonList(top4equipments), ConstantsUtils.INDEX_HTML, true);
      } else {
        return new MsgAndListDto<>(ConstantsFrontendPL.LOGIN_FAILED, Collections.singletonList(new Customer()), ConstantsFrontendPL.LOGIN_FORM_PAGE, false);
      }
    } else {
      return new MsgAndListDto<>(ConstantsFrontendPL.LOGIN_FAILED, Collections.singletonList(new Customer()), ConstantsFrontendPL.LOGIN_FORM_PAGE, false);
    }
  }

  public String logoutCustomer(final Model model) {
//        List<Equipment> top4equipments = getTop4Equipments(equipmentRepo.findAll());
//
//        return getRequestDispatcherWithDefaultMessage(model, // TODO - przeniesc do controllera
//                ConstantsFrontendPL.LOGIN_SUCCESS,
//                ConstantsFrontendPL.EQUIPMENT_SHORT_LIST,
//                top4equipments,
//                ConstantsFrontendPL.HOMEPAGE_URL);
    return null;
  }

  public MsgAndListDto<Customer> registerCustomer(final Customer customerRegisterData) {
    final List<Customer> customersFoundByEmail = customerRepo.findCustomersByEmail(customerRegisterData.getEmail());
    if (!Utils.isEmpty(customersFoundByEmail)) {
      return new MsgAndListDto<>(ConstantsFrontendPL.REGISTER_FAILED,
          Collections.singletonList(new Customer()), ConstantsFrontendPL.REGISTER_FORM_PAGE, false);
    } else {
      final Customer newCustomer = getNewCustomer(customerRegisterData, ConstantsUtils.FALSE, null);
      customerRepo.save(newCustomer);

      return new MsgAndListDto<>(ConstantsFrontendPL.REGISTER_SUCCESS,
          Collections.singletonList(new Customer()), ConstantsFrontendPL.LOGIN_FORM_PAGE, false);
    }
  }

  public String myAccountViewCustomer(final Model model) {
    return myAccountViewCustomer(null, model);
  }

  public String myAccountViewCustomer(final String message,
                                      final Model model) {
    //TODO dodac zamowienia

    // TODO przeniesc do controllera

//        return getRequestDispatcherWithDefaultMessage(model,
//                message,
//                ConstantsFrontendPL.LOGIN_CUSTOMER_OBJECT,
//                new Customer(),
//                ConstantsFrontendPL.MY_ACCOUNT_VIEW_URL);
    return null;
  }

  public String myAccountEditCustomer() {
    return ConstantsFrontendPL.MY_ACCOUNT_CREATE_URL;
  }

  public void myAccountUpdateCustomer(final Customer customerUpdateData,
                                      final Model model) {
    boolean isMoreCategoriesInDatabase = false;

    final Customer customerFoundById = customerRepo.getOne(customerUpdateData.getId());

    final List<Customer> customers = customerRepo.findCustomersByEmail(customerUpdateData.getEmail());
    Customer customerFoundByEmail = null;
    if (!Utils.isEmpty(customers) && customers.size() < 2) {
      customerFoundByEmail = customers.get(0);
    } else if (customers.size() > 1) {
      isMoreCategoriesInDatabase = true;
    }

    if (isMoreCategoriesInDatabase) {
      final String email = customerUpdateData.getEmail();
      final String message = ConstantsFrontendPL.MY_ACCOUNT_CUSTOMER_WAS_NOT_UPDATED
          + email + ConstantsFrontendPL.MY_ACCOUNT_CUSTOMER_NAME_ALREADY_EXIST_IN_DB;
      myAccountViewCustomer(message, model);
    } else if (customerFoundByEmail != null
        && !customerFoundByEmail.getId().equals(customerFoundById.getId())) {

      final String email = customerUpdateData.getEmail();
      final String message = ConstantsFrontendPL.MY_ACCOUNT_CUSTOMER_WAS_NOT_UPDATED
          + email + ConstantsFrontendPL.MY_ACCOUNT_CUSTOMER_NAME_ALREADY_EXIST_IN_DB;
      myAccountViewCustomer(message, model);
    } else {
      final Customer newCustomer = getNewCustomer(customerUpdateData, ConstantsUtils.TRUE, customerFoundById);
      final Customer customer = customerRepo.save(newCustomer);

      model.addAttribute(ConstantsFrontendPL.LOGGED_CUSTOMER, customer);

      myAccountViewCustomer(ConstantsFrontendPL.MY_ACCOUNT_CUSTOMER_WAS_UPDATED, model);
    }
  }

  private Customer getNewCustomer(final Customer customerData,
                                  final boolean isUpdate,
                                  final Customer customerDataById) {

    return Customer.builder()
        .id(isUpdate ? customerData.getId() : null)
        .email(customerData.getEmail())
        .password(isUpdate && customerData.getPassword().length() == 0
            ? customerDataById.getPassword() : customerData.getPassword())
        .firstName(customerData.getFirstName())
        .lastName(customerData.getLastName())
        .street(customerData.getStreet())
        .homeNumber(customerData.getHomeNumber())
        .city(customerData.getCity())
        .zipCode(customerData.getZipCode())
        .country(customerData.getCountry())
        .phone(customerData.getPhone())
        .registerDate(isUpdate ? customerDataById.getRegisterDate() : new Date())
        .build();
  }

}
