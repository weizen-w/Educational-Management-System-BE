package de.ait.ems.services.impl;

import static de.ait.ems.dto.UserDto.from;

import de.ait.ems.dto.NewUserDto;
import de.ait.ems.dto.UserDto;
import de.ait.ems.models.User;
import de.ait.ems.repositories.UsersRepository;
import de.ait.ems.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @project EducationalManagementSystem
 * @AUTHOR Oleksandr Zhurba on 17.10.2023.
 **/
@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {

  private final UsersRepository usersRepository;

  @Override
  public UserDto addUser(NewUserDto newUserDTO) {
    User user = User.builder()
        .username(newUserDTO.getUsername())
        .password(newUserDTO.getPassword())
        .email(newUserDTO.getEmail())
        .isBlocked(false)
        .build();
    usersRepository.save(user);
    return from(user);
  }
}
