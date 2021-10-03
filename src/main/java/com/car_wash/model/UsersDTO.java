package com.car_wash.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersDTO {

  private Integer id;

  @NotNull
  @Size(max = 45)
  private String username;

  @NotNull
  @Size(max = 45)
  private String password;

  @NotNull
  @Size(max = 45)
  private String user_role;
}
