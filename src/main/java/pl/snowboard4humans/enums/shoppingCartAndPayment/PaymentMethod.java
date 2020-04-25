package pl.snowboard4humans.enums.shoppingCartAndPayment;

public enum PaymentMethod {
    CASH_ON_DELIVERY("Gotówką przy odbiorze."),
    PREPAYMENT_TO_A_BANK_ACCOUNT("Płatność bankowa"),
    BLIK("BLIK"),
    PAYU("PayU");

    private String paymentMethod;

    PaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}
