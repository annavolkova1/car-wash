package com.car_wash.service;

import com.car_wash.domain.Amenities;
import com.car_wash.domain.ScheduledAmenities;
import com.car_wash.domain.Users;
import com.car_wash.model.ScheduledAmenitiesDTO;
import com.car_wash.repos.AmenitiesRepository;
import com.car_wash.repos.ScheduledAmenitiesRepository;
import com.car_wash.repos.UsersRepository;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ScheduledAmenitiesService {

  private final ScheduledAmenitiesRepository scheduledAmenitiesRepository;
  private final UsersRepository usersRepository;
  private final AmenitiesRepository amenitiesRepository;

  public ScheduledAmenitiesService(
      final ScheduledAmenitiesRepository scheduledAmenitiesRepository,
      final UsersRepository usersRepository, final AmenitiesRepository amenitiesRepository) {

    this.scheduledAmenitiesRepository = scheduledAmenitiesRepository;
    this.usersRepository = usersRepository;
    this.amenitiesRepository = amenitiesRepository;
  }

  public List<ScheduledAmenitiesDTO> findAll() {

    return scheduledAmenitiesRepository.findAll()
        .stream()
        .map(scheduledAmenities -> mapToDTO(scheduledAmenities, new ScheduledAmenitiesDTO()))
        .collect(Collectors.toList());
  }

  public ScheduledAmenitiesDTO get(final Integer id) {

    return scheduledAmenitiesRepository.findById(id)
        .map(scheduledAmenities -> mapToDTO(scheduledAmenities, new ScheduledAmenitiesDTO()))
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  public Integer create(final ScheduledAmenitiesDTO scheduledAmenitiesDTO) {

    final ScheduledAmenities scheduledAmenities = new ScheduledAmenities();
    mapToEntity(scheduledAmenitiesDTO, scheduledAmenities);

    return scheduledAmenitiesRepository.save(scheduledAmenities).getId();
  }

  public Integer waitingTime() {

    List<ScheduledAmenitiesDTO> list = findAll();
    ScheduledAmenities scheduledAmenities = new ScheduledAmenities();
    int result = 0;

    for (ScheduledAmenitiesDTO dto : list) {

      if (dto.isActive() && dto.isLive()) {
        mapToEntity(dto, scheduledAmenities);
        result += scheduledAmenities.getAmenity().getDurationMinutes();
      }
    }

    return result;
  }

  private ScheduledAmenitiesDTO mapToDTO(final ScheduledAmenities scheduledAmenities,
      final ScheduledAmenitiesDTO scheduledAmenitiesDTO) {

    scheduledAmenitiesDTO.setId(scheduledAmenities.getId());
    scheduledAmenitiesDTO.setStartTime(scheduledAmenities.getStartTime());
    scheduledAmenitiesDTO.setEndTime(scheduledAmenities.getEndTime());
    scheduledAmenitiesDTO.setUser(scheduledAmenities.getUser() == null ? null : scheduledAmenities.getUser().getId());
    scheduledAmenitiesDTO.setAmenity(scheduledAmenities.getAmenity() == null ? null : scheduledAmenities.getAmenity()
        .getId());
    scheduledAmenitiesDTO.setActive(scheduledAmenities.isActive());
    scheduledAmenitiesDTO.setLive(scheduledAmenities.isLive());

    return scheduledAmenitiesDTO;
  }


  private void mapToEntity(final ScheduledAmenitiesDTO scheduledAmenitiesDTO,
      final ScheduledAmenities scheduledAmenities) {

    if (scheduledAmenitiesDTO.getUser() != null && (scheduledAmenities.getUser() == null
        || !scheduledAmenities.getUser().getId().equals(scheduledAmenitiesDTO.getUser()))) {

      final Users user = usersRepository.findById(scheduledAmenitiesDTO.getUser())
          .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
      scheduledAmenities.setUser(user);
    }

    if (scheduledAmenitiesDTO.getAmenity() != null && (scheduledAmenities.getAmenity() == null
        || !scheduledAmenities.getAmenity().getId().equals(scheduledAmenitiesDTO.getAmenity()))) {

      final Amenities amenity = amenitiesRepository.findById(scheduledAmenitiesDTO.getAmenity())
          .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "amenity not found"));
      scheduledAmenities.setAmenity(amenity);
    }

    if (scheduledAmenitiesDTO.getStartTime() == null) {
      scheduledAmenities.setStartTime(OffsetDateTime.now());
      scheduledAmenities.setEndTime(
          OffsetDateTime.now()
              .plusMinutes(scheduledAmenities
                  .getAmenity()
                  .getDurationMinutes())
      );
    } else {
      scheduledAmenities.setStartTime(scheduledAmenitiesDTO.getStartTime());
      scheduledAmenities.setEndTime(scheduledAmenitiesDTO.getEndTime());
    }

    scheduledAmenities.setLive(scheduledAmenitiesDTO.isLive());
    scheduledAmenities.setActive(scheduledAmenitiesDTO.isActive());
  }
}
