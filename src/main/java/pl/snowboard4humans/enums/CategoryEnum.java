package pl.snowboard4humans.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoryEnum {
  SNOWBOARDS(1),
  SNOWBOARDSHOES(2),
  SNOWBOARDBINDINGS(3),
  SNOWBOARDGLOVES(4),
  SNOWBOARDGOGGLES(5),
  SNOWBOARDHELMETS(6),
  THERMOACTIVECLOTHING(7),
  ZERO(0);

  private final int category;
}
