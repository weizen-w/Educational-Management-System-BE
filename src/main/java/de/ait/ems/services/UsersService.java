package de.ait.ems.services;

import static de.ait.ems.dto.UserDTO.from;
import de.ait.ems.dto.NewUserDTO;
import de.ait.ems.dto.UserDTO;
import de.ait.ems.models.User;
import de.ait.ems.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @project EducationalManagementSystem
 * @AUTHOR Oleksandr Zhurba on 11.10.2023.
 **/
@RequiredArgsConstructor
@Service
public class UsersService {

  private final UsersRepository usersRepository;

  public UserDTO addUser(NewUserDTO newUserDTO) {
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
