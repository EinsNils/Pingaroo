package de.pingaroo.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
  USER("USER"),
  ADMIN("ADMIN");

  private final String name;
}
