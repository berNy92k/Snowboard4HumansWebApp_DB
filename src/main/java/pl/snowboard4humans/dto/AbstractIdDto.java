package pl.snowboard4humans.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractIdDto extends AbstractDto {

  private Integer id;
}
