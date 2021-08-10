package pl.snowboard4humans.controller.admin.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.snowboard4humans.constants.ConstantsAdminENG;
import pl.snowboard4humans.controller.frontend.SuperController;
import pl.snowboard4humans.dto.MsgAndListDto;
import pl.snowboard4humans.model.Order;
import pl.snowboard4humans.service.admin.OrderAdminService;

@Controller
@RequestMapping(value = "/admin/order")
public class OrderAdminController extends SuperController {

    private final OrderAdminService orderAdminService;

    @Autowired
    public OrderAdminController(final OrderAdminService orderAdminService) {
        this.orderAdminService = orderAdminService;
    }

    @GetMapping
    public String getOrder(final Model model) {
        final MsgAndListDto<Order> order = orderAdminService.getOrder();

        return getRequestDispatcherWithDefaultMessage(model,
                order.getMessage(),
                ConstantsAdminENG.ORDER_LIST_OBJECT,
                order.getListOfElements(),
                ConstantsAdminENG.ORDER_LIST_URL);
    }

    @GetMapping(value = "order_create")
    public String addNewOrderScreen(final Model model) {

        return getRequestDispatcherWithDefaultMessage(model,
                ConstantsAdminENG.CREATE_MODE_FILL_FIELDS_ORDER_ADMIN,
                ConstantsAdminENG.ORDER_OBJECT,
                new Order(),
                ConstantsAdminENG.ORDER_CREATE_OR_UPDATE_URL);
    }

}
