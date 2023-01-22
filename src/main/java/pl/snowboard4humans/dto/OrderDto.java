package pl.snowboard4humans.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto extends AbstractIdDto {

  private CustomerDto customer;
  private ShippingAddressDto shippingAddress;
  private String paymentMethod;
  private float total;
  private String status;
  private Date orderDate;
}
