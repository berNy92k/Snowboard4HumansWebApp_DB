package pl.snowboard4humans.service.frontend;

import org.springframework.stereotype.Service;
import pl.snowboard4humans.dto.MsgAndListDto;
import pl.snowboard4humans.model.shoppingCartAndPayment.ShoppingCart;

@Service
public class ShoppingCartService {

  public MsgAndListDto<ShoppingCart> viewShoppingCart() {
    return null;
  }

  public MsgAndListDto<ShoppingCart> addToShoppingCart(final Integer id) {
    return null;
  }

  public MsgAndListDto<ShoppingCart> clearShoppingCart() {
    return null;
  }

  public MsgAndListDto<ShoppingCart> deleteFromShoppingCart(final int id) {
    return null;
  }

  public MsgAndListDto<ShoppingCart> updateEquipmentInShoppingCart() {
    return null;
  }

  public MsgAndListDto<ShoppingCart> checkoutShoppingCart() {
    return null;
  }

  public MsgAndListDto<ShoppingCart> continueCheckoutShoppingCart() {
    return null;
  }
}
