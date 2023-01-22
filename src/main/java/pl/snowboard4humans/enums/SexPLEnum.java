package pl.snowboard4humans.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SexPLEnum {
  ALL("Wszystkie"),
  MAN("Mężczyzni"),
  WOMAN("Kobiety"),
  CHILD("Dzieci"),
  ZERO("Brak");

  private final String sex;
}
