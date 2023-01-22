package pl.snowboard4humans.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.snowboard4humans.enums.shoppingCartAndPayment.OrderStatus;
import pl.snowboard4humans.enums.shoppingCartAndPayment.PaymentMethod;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends AbstractModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id")
  private Integer id;
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

  public Integer getTotalOfOrder() {
    int quantity = 0;
    for (OrderEquipmentDetail orderEquipmentDetail : orderEquipmentDetails) {
      quantity = quantity + orderEquipmentDetail.getQuantity();
    }
    return quantity;
  }

  public void setPaymentMethod(PaymentMethod paymentMethod) {
    this.paymentMethod = paymentMethod.getPaymentMethod();
  }

  public void setStatus(OrderStatus status) {
    this.status = status.getOrderStatus();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Order order = (Order) o;
    return Float.compare(order.total, total) == 0 &&
        Objects.equals(id, order.id) &&
        Objects.equals(customer, order.customer) &&
        Objects.equals(shippingAddress, order.shippingAddress) &&
        Objects.equals(paymentMethod, order.paymentMethod) &&
        Objects.equals(status, order.status) &&
        Objects.equals(orderDate, order.orderDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, customer, shippingAddress, paymentMethod, total, status, orderDate);
  }
}
