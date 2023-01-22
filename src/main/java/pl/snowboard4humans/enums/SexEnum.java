package pl.snowboard4humans.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SexEnum {
  ALL("all"),
  MAN("MAN"),
  WOMAN("WOMAN"),
  CHILD("CHILD"),
  ZERO("0");

  private final String sex;
}
