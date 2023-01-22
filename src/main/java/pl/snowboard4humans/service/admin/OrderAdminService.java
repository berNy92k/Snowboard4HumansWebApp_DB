package pl.snowboard4humans.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.snowboard4humans.constants.ConstantsAdminENG;
import pl.snowboard4humans.dto.MsgAndListDto;
import pl.snowboard4humans.model.Order;
import pl.snowboard4humans.repository.OrderRepo;
import pl.snowboard4humans.service.SuperService;
import pl.snowboard4humans.utils.Utils;

import java.util.List;

@Service
public class OrderAdminService extends SuperService {

  private final OrderRepo orderRepo;

  @Autowired
  public OrderAdminService(final OrderRepo orderRepo) {
    this.orderRepo = orderRepo;
  }

  public MsgAndListDto<Order> getOrder() {
    final List<Order> orderList = orderRepo.findAll();

    final String message;
    if (Utils.isEmpty(orderList)) {
      message = ConstantsAdminENG.LACK_OF_ORDER_IN_DB;
    } else {
      message = ConstantsAdminENG.LIST_OF_ORDER_ADMIN;
    }

    return new MsgAndListDto<>(message, orderList);
  }

}
