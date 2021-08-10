package pl.snowboard4humans.dto;

import java.util.Date;

public class OrderDto extends AbstractIdDto {

    private CustomerDto customer;
    private ShippingAddressDto shippingAddress;
    private String paymentMethod;
    private float total;
    private String status;
    private Date orderDate;

    public OrderDto() {
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public ShippingAddressDto getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddressDto shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
