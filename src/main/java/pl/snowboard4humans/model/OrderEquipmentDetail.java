package pl.snowboard4humans.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Order_Equipment_Detail")
public class OrderEquipmentDetail extends AbstractModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_eq_detail_id")
  private Integer id;
  @NotNull
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "equipment_id")
  private Equipment equipment;
  @NotNull
  @Column(name = "quantity")
  private int quantity;
  @NotNull
  @Column(name = "subtotal")
  private float subtotal;
  @NotNull
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "order_id")
  private Order order;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    OrderEquipmentDetail that = (OrderEquipmentDetail) o;
    return quantity == that.quantity &&
        Float.compare(that.subtotal, subtotal) == 0 &&
        Objects.equals(id, that.id) &&
        Objects.equals(equipment, that.equipment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, equipment, quantity, subtotal);
  }
}
