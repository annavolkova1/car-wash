package com.car_wash.service;

import com.car_wash.domain.Users;
import com.car_wash.model.UsersDTO;
import com.car_wash.repos.UsersRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsersService {

  private final UsersRepository usersRepository;

  public UsersService(final UsersRepository usersRepository) {

    this.usersRepository = usersRepository;
  }

  public List<UsersDTO> findAll() {

    return usersRepository.findAll()
        .stream()
        .map(users -> mapToDTO(users, new UsersDTO()))
        .collect(Collectors.toList());
  }

  public UsersDTO get(final Integer id) {

    return usersRepository.findById(id)
        .map(users -> mapToDTO(users, new UsersDTO()))
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  public Integer create(final UsersDTO usersDTO) {

    final Users users = new Users();
    mapToEntity(usersDTO, users);

    return usersRepository.save(users).getId();
  }

  public void update(final Integer id, final UsersDTO usersDTO) {

    final Users users = usersRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    mapToEntity(usersDTO, users);
    usersRepository.save(users);
  }

  public void delete(final Integer id) {

    usersRepository.deleteById(id);
  }

  private UsersDTO mapToDTO(final Users users, final UsersDTO usersDTO) {

    usersDTO.setId(users.getId());
    usersDTO.setUsername(users.getUsername());
    usersDTO.setPassword(users.getPassword());
    usersDTO.setUser_role(users.getUser_role());

    return usersDTO;
  }

  private void mapToEntity(final UsersDTO usersDTO, final Users users) {

    users.setUsername(usersDTO.getUsername());
    users.setPassword(usersDTO.getPassword());
    users.setUser_role(usersDTO.getUser_role());
  }
}
