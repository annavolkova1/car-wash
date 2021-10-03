package com.car_wash.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AmenitiesDTO {

  private Integer id;

  @NotNull
  @Size(max = 255)
  private String amenityName;

  @NotNull
  private Integer durationMinutes;
}
