package com.car_wash.service;

import com.car_wash.domain.Amenities;
import com.car_wash.model.AmenitiesDTO;
import com.car_wash.repos.AmenitiesRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AmenitiesService {

  private final AmenitiesRepository amenitiesRepository;

  public AmenitiesService(final AmenitiesRepository amenitiesRepository) {

    this.amenitiesRepository = amenitiesRepository;
  }

  public List<AmenitiesDTO> findAll() {

    return amenitiesRepository.findAll()
        .stream()
        .map(amenities -> mapToDTO(amenities, new AmenitiesDTO()))
        .collect(Collectors.toList());
  }

  public AmenitiesDTO get(final Integer id) {

    return amenitiesRepository.findById(id)
        .map(amenities -> mapToDTO(amenities, new AmenitiesDTO()))
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  public Integer create(final AmenitiesDTO amenitiesDTO) {

    final Amenities amenities = new Amenities();
    mapToEntity(amenitiesDTO, amenities);
    return amenitiesRepository.save(amenities).getId();
  }

  public void update(final Integer id, final AmenitiesDTO amenitiesDTO) {

    final Amenities amenities = amenitiesRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    mapToEntity(amenitiesDTO, amenities);
    amenitiesRepository.save(amenities);
  }

  public void delete(final Integer id) {

    amenitiesRepository.deleteById(id);
  }

  private AmenitiesDTO mapToDTO(final Amenities amenities, final AmenitiesDTO amenitiesDTO) {

    amenitiesDTO.setId(amenities.getId());
    amenitiesDTO.setAmenityName(amenities.getAmenityName());
    amenitiesDTO.setDurationMinutes(amenities.getDurationMinutes());
    return amenitiesDTO;
  }

  private void mapToEntity(final AmenitiesDTO amenitiesDTO, final Amenities amenities) {

    amenities.setAmenityName(amenitiesDTO.getAmenityName());
    amenities.setDurationMinutes(amenitiesDTO.getDurationMinutes());
  }
}
