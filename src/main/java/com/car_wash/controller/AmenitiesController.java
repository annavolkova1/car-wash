package com.car_wash.controller;

import com.car_wash.model.AmenitiesDTO;
import com.car_wash.service.AmenitiesService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

  private static final Logger logger = LogManager.getLogger(AmenitiesController.class);

  private final AmenitiesService amenitiesService;

  public AmenitiesController(final AmenitiesService amenitiesService) {

    this.amenitiesService = amenitiesService;
  }

  @GetMapping
  public ResponseEntity<List<AmenitiesDTO>> getAllAmenities() {

    logger.info("getting all Amenities");

    return ResponseEntity.ok(amenitiesService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<AmenitiesDTO> getAmenity(@PathVariable final Integer id) {

    logger.info("getting the Amenity, Id:" + id);
    return ResponseEntity.ok(amenitiesService.get(id));
  }

  @PostMapping
  public ResponseEntity<Integer> createAmenity(
      @RequestBody @Valid final AmenitiesDTO amenitiesDTO) {

    logger.info("creating the Amenity");
    return new ResponseEntity<>(amenitiesService.create(amenitiesDTO), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateAmenity(@PathVariable final Integer id,
      @RequestBody @Valid final AmenitiesDTO amenitiesDTO) {

    logger.info("updating the Amenity, Id:" + id);
    amenitiesService.update(id, amenitiesDTO);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAmenity(@PathVariable final Integer id) {

    logger.info("deleting the Amenity, Id:" + id);
    amenitiesService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
