package pl.snowboard4humans.dto;

public class OrderEquipmentDetailDto extends AbstractIdDto {

    private EquipmentDto equipment;
    private int quantity;
    private float subtotal;
    private OrderDto order;

    public OrderEquipmentDetailDto() {
    }

    public EquipmentDto getEquipment() {
        return equipment;
    }

    public void setEquipment(EquipmentDto equipment) {
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

    public OrderDto getOrder() {
        return order;
    }

    public void setOrder(OrderDto order) {
        this.order = order;
    }
}
