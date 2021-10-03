package com.car_wash.domain;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Amenities {

  @Id
  @Column(nullable = false, updatable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false)
  private String amenityName;

  @Column(nullable = false)
  private Integer durationMinutes;

  @OneToMany(mappedBy = "amenity")
  private Set<ScheduledAmenities> amenityScheduledAmenities;
}
