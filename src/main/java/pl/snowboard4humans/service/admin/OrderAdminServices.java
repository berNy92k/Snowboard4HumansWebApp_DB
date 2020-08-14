package pl.snowboard4humans.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.snowboard4humans.constants.ConstantsAdminENG;
import pl.snowboard4humans.model.Order;
import pl.snowboard4humans.repository.OrderRepo;
import pl.snowboard4humans.service.SuperService;
import pl.snowboard4humans.utils.Utils;

import java.util.List;

@Service
public class OrderAdminServices extends SuperService {

    private OrderRepo orderRepo;

    @Autowired
    public OrderAdminServices(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public String getOrder(Model model) {
        String message = ConstantsAdminENG.LIST_OF_ORDER_ADMIN;

        List<Order> orderList = orderRepo.findAll();

        // if order list is empty then message change
        if (Utils.isEmpty(orderList)) {
            message = ConstantsAdminENG.LACK_OF_ORDER_IN_DB;
        }

        return getRequestDispatcher(model,
                message,
                ConstantsAdminENG.ORDER_LIST_OBJECT,
                orderList,
                ConstantsAdminENG.ORDER_LIST_URL);
    }

    public String getAddNewOrderScreen(Model model) {
        return getRequestDispatcher(model,
                ConstantsAdminENG.CREATE_MODE_FILL_FIELDS_ORDER_ADMIN,
                ConstantsAdminENG.ORDER_OBJECT,
                new Order(),
                ConstantsAdminENG.ORDER_CREATE_OR_UPDATE_URL);
    }

}
