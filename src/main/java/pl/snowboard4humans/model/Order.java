package pl.snowboard4humans.model;

import com.sun.istack.internal.NotNull;
import pl.snowboard4humans.enums.shoppingCartAndPayment.OrderStatus;
import pl.snowboard4humans.enums.shoppingCartAndPayment.PaymentMethod;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_address_id")
    private ShippingAddress shippingAddress;
    @NotNull
    @Column(name = "payment_method")
    private String paymentMethod;
    @NotNull
    @Column(name = "total")
    private float total;
    @NotNull
    @Column(name = "status")
    private String status;
    @NotNull
    @Column(name = "order_date")
    private Date orderDate;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "order")
    private Set<OrderEquipmentDetail> orderEquipmentDetails = new HashSet<>();

    public Order() {
    }

    public Integer getTotalOfOrder() {
        int quantity = 0;
        for (OrderEquipmentDetail orderEquipmentDetail : orderEquipmentDetails) {
            quantity = quantity + orderEquipmentDetail.getQuantity();
        }
        return quantity;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod.getPaymentMethod();
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

    public void setStatus(OrderStatus status) {
        this.status = status.getOrderStatus();
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Set<OrderEquipmentDetail> getOrderEquipmentDetails() {
        return orderEquipmentDetails;
    }

    public void setOrderEquipmentDetails(Set<OrderEquipmentDetail> orderEquipmentDetails) {
        this.orderEquipmentDetails = orderEquipmentDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Float.compare(order.total, total) == 0 &&
                Objects.equals(orderId, order.orderId) &&
                Objects.equals(customer, order.customer) &&
                Objects.equals(shippingAddress, order.shippingAddress) &&
                Objects.equals(paymentMethod, order.paymentMethod) &&
                Objects.equals(status, order.status) &&
                Objects.equals(orderDate, order.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, customer, shippingAddress, paymentMethod, total, status, orderDate);
    }
}
