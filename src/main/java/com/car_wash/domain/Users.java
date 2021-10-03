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
public class Users {

  @Id
  @Column(nullable = false, updatable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false, length = 45)
  private String username;

  @Column(nullable = false, length = 45)
  private String password;

  @OneToMany(mappedBy = "user")
  private Set<ScheduledAmenities> userScheduledAmenities;

  @Column(nullable = false, length = 45)
  private String user_role;
}
