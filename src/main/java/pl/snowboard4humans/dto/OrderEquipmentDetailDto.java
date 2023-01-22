package pl.snowboard4humans.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderEquipmentDetailDto extends AbstractIdDto {

  private EquipmentDto equipment;
  private int quantity;
  private float subtotal;
  private OrderDto order;
}
