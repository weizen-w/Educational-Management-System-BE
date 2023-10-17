package de.ait.ems.controllers;

import de.ait.ems.controllers.api.UsersApi;
import de.ait.ems.dto.NewUserDto;
import de.ait.ems.dto.UserDto;
import de.ait.ems.services.UsersService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
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
  public UserDto addUser(@RequestBody @Valid NewUserDto newUser) {
    return usersService.addUser(newUser);
  }
}
