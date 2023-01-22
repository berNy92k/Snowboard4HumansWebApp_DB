package pl.snowboard4humans.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDto extends AbstractIdDto {

  private int rating;
  private String headline;
  private String comment;
  private Date reviewTime;
  private EquipmentDto equipment;
  private CustomerDto customer;

  private String customerEmail;
}
