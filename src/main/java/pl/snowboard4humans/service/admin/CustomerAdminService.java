package pl.snowboard4humans.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.snowboard4humans.constants.ConstantsAdminENG;
import pl.snowboard4humans.constants.ConstantsPL;
import pl.snowboard4humans.dto.MsgAndListDto;
import pl.snowboard4humans.model.Customer;
import pl.snowboard4humans.repository.CustomerRepo;
import pl.snowboard4humans.service.SuperService;
import pl.snowboard4humans.utils.Utils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerAdminService extends SuperService {

    private final CustomerRepo customerRepo;

    @Autowired
    public CustomerAdminService(final CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public MsgAndListDto<Customer> getCustomers() {
        final List<Customer> customerList = customerRepo.findAll();

        final String message;
        if (Utils.isEmpty(customerList)) {
            message = ConstantsAdminENG.LACK_OF_CUSTOMER_IN_DB;
        } else {
            message = ConstantsAdminENG.LIST_OF_CUSTOMER_ADMIN;
        }

        return new MsgAndListDto<>(message, customerList);
    }

    public MsgAndListDto<Customer> addOrUpdateCustomer(final Customer customer) {
        final Integer customerId = customer.getId();
        final String email = customer.getEmail();
        final List<Customer> customersByEmail = customerRepo.findCustomersByEmail(email);

        String message;
        if (customerId == null) {
            message = ConstantsAdminENG.NEW_CUSTOMER_WAS_NOT_CREATED + email + ConstantsAdminENG.CUSTOMER_NAME_ALREADY_EXIST_IN_DB;
            final MsgAndListDto<Customer> customerMsgAndListDto = checkIfUserAlreadyExistInDbByEmail(customersByEmail, message);
            if (customerMsgAndListDto != null) {
                return customerMsgAndListDto;
            }
            customer.setRegisterDate(new Date());
        } else {
            message = ConstantsAdminENG.CUSTOMER_WAS_NOT_UPDATED + email + ConstantsAdminENG.CUSTOMER_NAME_ALREADY_EXIST_IN_DB;

            final MsgAndListDto<Customer> customerMsgAndListDtoByEmail = checkIfUserAlreadyExistInDbByEmail(customersByEmail, message);
            if (customerMsgAndListDtoByEmail != null) {
                return customerMsgAndListDtoByEmail;
            }

            final Customer customerById = customerRepo.findCustomerById(customerId);
            final MsgAndListDto<Customer> customerMsgAndListDtoById = checkIfUserAlreadyExistInDbById(
                    customersByEmail.size() > 0 ? customersByEmail.get(0) : null,
                    message,
                    customerById);
            if (customerMsgAndListDtoById != null) {
                return customerMsgAndListDtoById;
            }

            if (customer.getPassword().length() == 0) {
                customer.setPassword(customerById.getPassword());
            }
            customer.setRegisterDate(customerById.getRegisterDate());
        }

        final Customer customerSaved = customerRepo.save(customer);
        if (customerId == null && customerSaved != null && customerSaved.getId() != null) {
            message = ConstantsAdminENG.NEW_CUSTOMER_WAS_CREATED;
        } else if (customerId != null && customerSaved != null && customerSaved.getId() != null) {
            message = ConstantsAdminENG.CUSTOMER_WAS_UPDATED;
        } else if (customerId == null && customerSaved == null) {
            message = ConstantsAdminENG.NEW_CUSTOMER_WAS_NOT_CREATED;
        } else {
            message = ConstantsAdminENG.CUSTOMER_WAS_NOT_UPDATED;
        }

        return new MsgAndListDto<>(message, customerRepo.findAll());
    }

    private MsgAndListDto<Customer> checkIfUserAlreadyExistInDbById(final Customer customerByEmail,
                                                                    final String message,
                                                                    final Customer customerById) {
        if (customerByEmail != null && !customerByEmail.getId().equals(customerById.getId())) {
            return new MsgAndListDto<>(message, customerRepo.findAll());
        }
        return null;
    }

    private MsgAndListDto<Customer> checkIfUserAlreadyExistInDbByEmail(final List<Customer> customersByEmail,
                                                                       final String message) {
        if (customersByEmail.size() > 0) {
            return new MsgAndListDto<>(message, customerRepo.findAll());
        }
        return null;
    }

    public MsgAndListDto<Customer> getEditCustomerScreen(final int customerId) {
        final Optional<Customer> customerOptional = customerRepo.findById(customerId);
        final Customer customer = customerOptional.orElseGet(Customer::new);

        return new MsgAndListDto<>(ConstantsPL.EMPTY_MESSAGE, Collections.singletonList(customer));
    }

    public MsgAndListDto<Customer> getViewCustomerScreen(final int customerId) {
        final Optional<Customer> customerOptional = customerRepo.findById(customerId);
        final Customer customer = customerOptional.orElseGet(Customer::new);

        return new MsgAndListDto<>(ConstantsPL.EMPTY_MESSAGE, Collections.singletonList(customer));
    }

    public MsgAndListDto<Customer> deleteCustomer(final int customerId) {
        final Optional<Customer> customerOptional = customerRepo.findById(customerId);

        String message;
        if (customerOptional.isPresent()) {
            customerRepo.deleteById(customerId);
            message = ConstantsAdminENG.CUSTOMER_WAS_DELETED;
        } else {
            message = ConstantsAdminENG.COULD_NOT_FIND_CUSTOMER_BY_ID + ConstantsAdminENG.DELETED_BY_ANOTHER_CUSTOMER_ADMIN;
        }

        final List<Customer> customerList = customerRepo.findAll();
        // if customer list is empty then message change
        if (Utils.isEmpty(customerList)) {
            message = ConstantsAdminENG.LACK_OF_CUSTOMER_IN_DB + message;
        }

        return new MsgAndListDto<>(message, customerList);
    }

    public List<Customer> findAll() {
        return customerRepo.findAll();
    }
}
