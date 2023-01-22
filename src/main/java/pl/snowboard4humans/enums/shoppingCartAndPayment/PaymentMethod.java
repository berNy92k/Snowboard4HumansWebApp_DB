package pl.snowboard4humans.enums.shoppingCartAndPayment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentMethod {
  CASH_ON_DELIVERY("Gotówką przy odbiorze."),
  PREPAYMENT_TO_A_BANK_ACCOUNT("Płatność bankowa"),
  BLIK("BLIK"),
  PAYU("PayU");

  private final String paymentMethod;
}
