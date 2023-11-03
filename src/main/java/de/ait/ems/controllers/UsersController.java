package de.ait.ems.controllers;

import de.ait.ems.controllers.api.UsersApi;
import de.ait.ems.dto.NewUserDto;
import de.ait.ems.dto.UpdateUserDto;
import de.ait.ems.dto.UserDto;
import de.ait.ems.models.User.Role;
import de.ait.ems.security.details.AuthenticatedUser;
import de.ait.ems.services.UsersService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @project EducationalManagementSystem
 * @AUTHOR Oleksandr Zhurba on 11.10.2023.
 **/
@RestController
@RequiredArgsConstructor
public class UsersController implements UsersApi {

  private final UsersService usersService;

  @Override
  public List<UserDto> getAllUsers() {
    return usersService.getAllUsers();
  }

  @Override
  public List<UserDto> getUsersByRole(Role role) {
    return usersService.getUsersByRole(role);
  }

  @Override
  public UserDto register(NewUserDto newUser) {
    return usersService.register(newUser);
  }

  @Override
  public UserDto getConfirmation(String confirmCode) {
    return usersService.confirm(confirmCode);
  }

  @Override
  public UserDto getProfile(AuthenticatedUser user) {
    Long currentUserId = user.getId();
    return usersService.getUserById(currentUserId);
  }

  @Override
  public UserDto updateUser(Long userId, UpdateUserDto updateUser) {
    return usersService.updateUser(userId, updateUser);
  }
}
