package pl.snowboard4humans.controller.admin.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.snowboard4humans.service.admin.OrderAdminServices;

@Controller
@RequestMapping(value = "/admin/order")
public class OrderAdminController {

    private OrderAdminServices orderAdminServices;

    @Autowired
    public OrderAdminController(OrderAdminServices orderAdminServices) {
        this.orderAdminServices = orderAdminServices;
    }

    @GetMapping
    public String getOrder(Model model) {
        return orderAdminServices.getOrder(model);
    }

    @GetMapping(value = "order_create")
    public String addNewOrderScreen(Model model) {
        return orderAdminServices.getAddNewOrderScreen(model);
    }

}
