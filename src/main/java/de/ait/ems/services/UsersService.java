package de.ait.ems.services;

import de.ait.ems.dto.NewUserDto;
import de.ait.ems.dto.UserDto;

/**
 * @project EducationalManagementSystem
 * @AUTHOR Oleksandr Zhurba on 17.10.2023.
 **/
public interface UsersService {

  UserDto addUser(NewUserDto newUserDTO);
}
