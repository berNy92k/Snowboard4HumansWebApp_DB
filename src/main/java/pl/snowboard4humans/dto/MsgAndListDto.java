package pl.snowboard4humans.dto;

import pl.snowboard4humans.model.Customer;
import pl.snowboard4humans.model.shoppingCartAndPayment.TransactionData;

import java.util.List;

public class MsgAndListDto<TYPE> {

    private String message;
    private List<TYPE> listOfElements;
    private String url;

    private boolean isAnonymous;
    private Customer customer;

    private TransactionData transactionData;

    public MsgAndListDto() {
    }

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<TYPE> getListOfElements() {
        return listOfElements;
    }

    public void setListOfElements(List<TYPE> listOfElements) {
        this.listOfElements = listOfElements;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public TransactionData getTransactionData() {
        return transactionData;
    }

    public void setTransactionData(TransactionData transactionData) {
        this.transactionData = transactionData;
    }
}
