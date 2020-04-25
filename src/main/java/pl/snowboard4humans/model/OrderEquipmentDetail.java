package pl.snowboard4humans.model;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Order_Equipment_Detail")
public class OrderEquipmentDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_eq_detail_id")
    private Integer orderEquipmentDetailId;
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

    public OrderEquipmentDetail() {
    }

    public Integer getOrderEquipmentDetailId() {
        return orderEquipmentDetailId;
    }

    public void setOrderEquipmentDetailId(Integer orderEquipmentDetailId) {
        this.orderEquipmentDetailId = orderEquipmentDetailId;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEquipmentDetail that = (OrderEquipmentDetail) o;
        return quantity == that.quantity &&
                Float.compare(that.subtotal, subtotal) == 0 &&
                Objects.equals(orderEquipmentDetailId, that.orderEquipmentDetailId) &&
                Objects.equals(equipment, that.equipment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderEquipmentDetailId, equipment, quantity, subtotal);
    }
}
