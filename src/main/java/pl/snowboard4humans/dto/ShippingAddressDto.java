package pl.snowboard4humans.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShippingAddressDto extends AbstractIdDto {

  private String email;
  private String firstName;
  private String lastName;
  private String streetName;
  private String houseOrApartmentNr;
  private String city;
  private String zipCode;
  private String country;
  private String phone;
}
