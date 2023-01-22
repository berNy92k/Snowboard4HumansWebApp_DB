package pl.snowboard4humans.model.shoppingCartAndPayment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.snowboard4humans.model.Customer;
import pl.snowboard4humans.model.ShippingAddress;

@Getter
@Builder
@AllArgsConstructor
public class TransactionData {

  private final ShippingAddress shippingAddress;
  private final ShoppingCart shoppingCart;
  private final Customer customer;
}
