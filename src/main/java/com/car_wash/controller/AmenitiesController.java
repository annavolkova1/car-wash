package com.car_wash.controller;

import com.car_wash.model.AmenitiesDTO;
import com.car_wash.service.AmenitiesService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/amenities", produces = MediaType.APPLICATION_JSON_VALUE)
@SecurityRequirement(name = "car-wash")
public class AmenitiesController {

  private final AmenitiesService amenitiesService;

  public AmenitiesController(final AmenitiesService amenitiesService) {

    this.amenitiesService = amenitiesService;
  }

  @GetMapping
  public ResponseEntity<List<AmenitiesDTO>> getAllAmenities() {

    return ResponseEntity.ok(amenitiesService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<AmenitiesDTO> getAmenities(@PathVariable final Integer id) {

    return ResponseEntity.ok(amenitiesService.get(id));
  }

  @PostMapping
  public ResponseEntity<Integer> createAmenities(
      @RequestBody @Valid final AmenitiesDTO amenitiesDTO) {

    return new ResponseEntity<>(amenitiesService.create(amenitiesDTO), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateAmenities(@PathVariable final Integer id,
      @RequestBody @Valid final AmenitiesDTO amenitiesDTO) {

    amenitiesService.update(id, amenitiesDTO);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAmenities(@PathVariable final Integer id) {

    amenitiesService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
