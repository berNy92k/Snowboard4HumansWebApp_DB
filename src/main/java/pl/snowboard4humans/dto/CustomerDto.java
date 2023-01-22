package pl.snowboard4humans.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDto extends AbstractIdDto {

  private String email;
  private String password;
  private String firstName;
  private String lastName;
  private String street;
  private String homeNumber;
  private String city;
  private String zipCode;
  private String country;
  private String phone;
  private Date registerDate;
}
