package pl.snowboard4humans.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.snowboard4humans.model.Customer;
import pl.snowboard4humans.model.shoppingCartAndPayment.TransactionData;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MsgAndListDto<TYPE> {

  private String message;
  private List<TYPE> listOfElements;
  private String url;

  private boolean isAnonymous;
  private Customer customer;

  private TransactionData transactionData;

  public MsgAndListDto(final String message, final List<TYPE> listOfElements) {
    this.message = message;
    this.listOfElements = listOfElements;
  }

  public MsgAndListDto(final String message, final List<TYPE> listOfElements, final String url) {
    this.message = message;
    this.listOfElements = listOfElements;
    this.url = url;
  }

  public MsgAndListDto(final String message, final List<TYPE> listOfElements, final String url, final boolean isAnonymous) {
    this.message = message;
    this.listOfElements = listOfElements;
    this.url = url;
    this.isAnonymous = isAnonymous;
  }

  public MsgAndListDto(final String message, final List<TYPE> listOfElements, final String url, final boolean isAnonymous, final Customer customer) {
    this.message = message;
    this.listOfElements = listOfElements;
    this.url = url;
    this.isAnonymous = isAnonymous;
    this.customer = customer;
  }

  public MsgAndListDto(final String message, final List<TYPE> listOfElements, final String url, final boolean isAnonymous, final Customer customer, final TransactionData transactionData) {
    this.message = message;
    this.listOfElements = listOfElements;
    this.url = url;
    this.isAnonymous = isAnonymous;
    this.customer = customer;
    this.transactionData = transactionData;
  }
}
