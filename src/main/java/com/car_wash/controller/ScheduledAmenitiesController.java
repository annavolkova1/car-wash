package com.car_wash.controller;

import com.car_wash.model.ScheduledAmenitiesDTO;
import com.car_wash.service.ScheduledAmenitiesService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/scheduledAmenities", produces = MediaType.APPLICATION_JSON_VALUE)
public class ScheduledAmenitiesController {

  private static final Logger logger = LogManager.getLogger(ScheduledAmenitiesController.class);

  private final ScheduledAmenitiesService scheduledAmenitiesService;

  public ScheduledAmenitiesController(final ScheduledAmenitiesService scheduledAmenitiesService) {

    this.scheduledAmenitiesService = scheduledAmenitiesService;
  }

  @GetMapping("/all")
  public ResponseEntity<List<ScheduledAmenitiesDTO>> getAllScheduledAmenities() {

    logger.info("getting all ScheduledAmenities");
    return ResponseEntity.ok(scheduledAmenitiesService.findAll());
  }

  @Operation(summary = "startTime and endTime must be null for this request.")
  @PostMapping("/live")
  public ResponseEntity<Integer> createScheduledAmenityLive(
      @RequestBody @Valid final ScheduledAmenitiesDTO scheduledAmenitiesDTO) {

    logger.info("creating the ScheduledAmenityLive");
    return new ResponseEntity<>(scheduledAmenitiesService.create(scheduledAmenitiesDTO), HttpStatus.CREATED);
  }

  @PostMapping
  public ResponseEntity<Integer> createScheduledAmenity(
      @RequestBody @Valid final ScheduledAmenitiesDTO scheduledAmenitiesDTO) {

    logger.info("creating the ScheduledAmenityPlanned");
    return new ResponseEntity<>(scheduledAmenitiesService.create(scheduledAmenitiesDTO), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<Integer> findOutTheWaitingTime() {

    logger.info("getting the waiting time");
    return ResponseEntity.ok(scheduledAmenitiesService.waitingTime());
  }
}
