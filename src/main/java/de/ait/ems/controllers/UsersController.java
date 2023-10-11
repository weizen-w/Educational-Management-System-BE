package de.ait.ems.controllers;

import de.ait.ems.dto.NewUserDTO;
import de.ait.ems.dto.UserDTO;
import de.ait.ems.services.UsersService;
import de.ait.ems.validations.dto.ValidationErrorsDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @project EducationalManagementSystem
 * @AUTHOR Oleksandr Zhurba on 11.10.2023.
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Tags(value = {
    @Tag(name = "Users")
})
public class UsersController {

  private final UsersService usersService;

  @Operation(summary = "Create an user", description = "Available to administrator")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201",
          description = "The user was created successfully",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = UserDTO.class))),
      @ApiResponse(responseCode = "400",
          description = "Validation error",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = ValidationErrorsDTO.class)))
  })
  @PostMapping
  public ResponseEntity<UserDTO> addUser(@RequestBody @Valid NewUserDTO newUser) {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(usersService.addUser(newUser));
  }
}
