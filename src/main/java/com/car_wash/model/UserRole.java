package com.car_wash.model;

import java.util.stream.Stream;

public enum UserRole {

  ADMIN("ADMIN"),
  USER("USER");

  private final String value;

  UserRole(String value) {
    this.value = value;
  }

  public String getAuthorityTitle() {

    return "ROLE_" + value;
  }

  public static String[] getAllowedAuthorities() {

    return Stream.of(UserRole.USER, UserRole.ADMIN)
        .map(UserRole::getAuthorityTitle)
        .toArray(String[]::new);
  }

  public String getValue() {
    return value;
  }
}
