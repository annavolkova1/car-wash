package com.car_wash.controller;

import com.car_wash.model.UsersDTO;
import com.car_wash.service.UsersService;
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
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
@SecurityRequirement(name = "car-wash")
public class UsersController {

  private static final Logger logger = LogManager.getLogger(UsersController.class);

  private final UsersService usersService;

  public UsersController(final UsersService usersService) {

    this.usersService = usersService;
  }

  @GetMapping
  public ResponseEntity<List<UsersDTO>> getAllUsers() {

    logger.info("getting all Users");
    return ResponseEntity.ok(usersService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<UsersDTO> getUser(@PathVariable final Integer id) {

    logger.info("getting the User, Id:" + id);
    return ResponseEntity.ok(usersService.get(id));
  }

  @PostMapping
  public ResponseEntity<Integer> createUser(@RequestBody @Valid final UsersDTO usersDTO) {

    logger.info("creating the User");
    return new ResponseEntity<>(usersService.create(usersDTO), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateUser(@PathVariable final Integer id,
      @RequestBody @Valid final UsersDTO usersDTO) {

    logger.info("updating the User, Id:" + id);
    usersService.update(id, usersDTO);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable final Integer id) {

    logger.info("deleting the User, Id:" + id);
    usersService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
