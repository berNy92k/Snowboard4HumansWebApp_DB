package pl.snowboard4humans.enums.shoppingCartAndPayment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
  ORDER_NEW("Nowe zamówienie."),
  ORDER_IN_PROGRESS("W trakcie realizacji."),
  ORDER_IS_READY_TO_SEND_TO_CLIENT("Zamówienie gotowe do wysyłki."),
  ORDER_SENT_TO_CLIENT("Zamówienie wysłane do klienta."),
  ORDER_BACK_AS_NOT_PICKED_UP_BY_CLIENT("Zamówienie wróciło do sklepu (brak odbioru przez klienta)"),
  ORDER_RECEIVED_BY_CLIENT("Zamówienie odebrane przez klienta"),
  ORDER_CANCELED_BY_SHOP("Zamówienie anulowane przez sklep"),
  ORDER_CANCELED_BY_CLIENT("Zamówienie anulowane przez klienta");

  private final String orderStatus;
}
