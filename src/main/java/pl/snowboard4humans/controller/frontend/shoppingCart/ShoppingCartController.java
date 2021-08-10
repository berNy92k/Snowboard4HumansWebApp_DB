package pl.snowboard4humans.controller.frontend.shoppingCart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.snowboard4humans.constants.ConstantsFrontendPL;
import pl.snowboard4humans.controller.frontend.SuperController;
import pl.snowboard4humans.dto.MsgAndListDto;
import pl.snowboard4humans.model.shoppingCartAndPayment.ShoppingCart;
import pl.snowboard4humans.service.frontend.ShoppingCartServices;

@Controller
@RequestMapping(value = "/homepage/shoppingCart")
public class ShoppingCartController extends SuperController {

    private final ShoppingCartServices shoppingCartServices;

    @Autowired
    public ShoppingCartController(final ShoppingCartServices shoppingCartServices) {
        this.shoppingCartServices = shoppingCartServices;
    }

    @GetMapping
    public String viewShoppingCart(final Model model) {
        final MsgAndListDto<ShoppingCart> shoppingCart = shoppingCartServices.viewShoppingCart();

        return getRequestDispatcherWithDefaultMessage(model,
                shoppingCart.getMessage(),
                ConstantsFrontendPL.SHOPPING_CART_OBJECT,
                shoppingCart.getListOfElements().get(0),
                ConstantsFrontendPL.SHOPPING_CART_HOMEPAGE_URL);
    }

    @GetMapping(value = "/addToShoppingCart")
    public String addToShoppingCartScreen(final Model model,
                                          @RequestParam(value = "eqmId") final Integer id) {
        final MsgAndListDto<ShoppingCart> shoppingCart = shoppingCartServices.addToShoppingCart(id);

        return getRequestDispatcherWithDefaultMessage(model,
                shoppingCart.getMessage(),
                ConstantsFrontendPL.SHOPPING_CART_OBJECT,
                shoppingCart.getListOfElements().get(0),
                ConstantsFrontendPL.SHOPPING_CART_HOMEPAGE_URL);
    }

    @GetMapping(value = "/clearShoppingCart")
    public String clearShoppingCart(final Model model) {
        final MsgAndListDto<ShoppingCart> shoppingCart = shoppingCartServices.clearShoppingCart();

        return getRequestDispatcherWithDefaultMessage(model,
                shoppingCart.getMessage(),
                ConstantsFrontendPL.SHOPPING_CART_OBJECT,
                shoppingCart.getListOfElements().get(0),
                ConstantsFrontendPL.SHOPPING_CART_HOMEPAGE_URL);
    }

    @GetMapping(value = "/deleteFromShoppingCart")
    public String deleteFromShoppingCart(final Model model,
                                         @RequestParam(value = "eq") final int id) {
        final MsgAndListDto<ShoppingCart> shoppingCart = shoppingCartServices.deleteFromShoppingCart(id);

        return getRequestDispatcherWithDefaultMessage(model,
                shoppingCart.getMessage(),
                ConstantsFrontendPL.SHOPPING_CART_OBJECT,
                shoppingCart.getListOfElements().get(0),
                ConstantsFrontendPL.SHOPPING_CART_HOMEPAGE_URL);
    }

    @PostMapping(value = "/updateEquipmentInShoppingCart")
    public String updateEquipmentInShoppingCart(final Model model,
                                                @ModelAttribute(name = "shoppingCart") final ShoppingCart shoppingCart) {
        final MsgAndListDto<ShoppingCart> shoppingCartMsgAndListDto = shoppingCartServices.updateEquipmentInShoppingCart();

        return getRequestDispatcherWithDefaultMessage(model,
                shoppingCartMsgAndListDto.getMessage(),
                ConstantsFrontendPL.SHOPPING_CART_OBJECT,
                shoppingCartMsgAndListDto.getListOfElements().get(0),
                ConstantsFrontendPL.SHOPPING_CART_HOMEPAGE_URL);
    }

    @GetMapping(value = "/checkout")
    public String checkout(final Model model,
                           @ModelAttribute(name = "shoppingCart") final ShoppingCart shoppingCart) {
        final MsgAndListDto<ShoppingCart> scmal = shoppingCartServices.checkoutShoppingCart();

        if (!scmal.isAnonymous()) {
            model.addAttribute("loggedCustomer", scmal.getCustomer());
        }

        return getRequestDispatcherWithDefaultMessage(model,
                scmal.getMessage(),
                ConstantsFrontendPL.SHOPPING_CART_OBJECT,
                scmal.getListOfElements().get(0),
                scmal.getUrl());
    }

    @PostMapping(value = "/continueCheckout")
    public String continueCheckout(Model model) {
        final MsgAndListDto<ShoppingCart> scmal = shoppingCartServices.continueCheckoutShoppingCart();

        if (!scmal.isAnonymous()) {
            model.addAttribute("loggedCustomer", scmal.getCustomer());
            model.addAttribute("transactionData", scmal.getTransactionData());
        }

        return getRequestDispatcherWithDefaultMessage(model,
                scmal.getMessage(),
                ConstantsFrontendPL.SHOPPING_CART_OBJECT,
                scmal.getListOfElements().get(0),
                scmal.getUrl());
    }
}
