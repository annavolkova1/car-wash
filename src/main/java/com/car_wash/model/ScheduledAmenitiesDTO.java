package com.car_wash.model;

import java.time.OffsetDateTime;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ScheduledAmenitiesDTO {

  private Integer id;

  private OffsetDateTime startTime;

  private OffsetDateTime endTime;

  @NotNull
  private Integer amenity;

  @NotNull
  private Integer user;

  @NotNull
  private boolean active;

  @NotNull
  private boolean live;
}
