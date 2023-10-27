package de.ait.ems.controllers.api;

import de.ait.ems.dto.GroupDto;
import de.ait.ems.dto.NewUserDto;
import de.ait.ems.dto.StandardResponseDto;
import de.ait.ems.dto.UserDto;
import de.ait.ems.security.details.AuthenticatedUser;
import de.ait.ems.validations.dto.ValidationErrorsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * 17/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@RequestMapping("/api/users")
@Tags(value = {@Tag(name = "Users")})
public interface UsersApi {

  @Operation(summary = "Create an user", description = "Available to administrator")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201",
          description = "The user was created successfully",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = UserDto.class))),
      @ApiResponse(responseCode = "400",
          description = "Validation error",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = ValidationErrorsDto.class))),
      @ApiResponse(responseCode = "409",
          description = "There is already a user with this email",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = StandardResponseDto.class)))
  })
  @PostMapping("/register")
  @ResponseStatus(code = HttpStatus.CREATED)
  UserDto register(@RequestBody @Valid NewUserDto newUser);

  @GetMapping("/confirm/{confirm-code}")
  UserDto getConfirmation(@PathVariable("confirm-code") String confirmCode);

  @GetMapping("/profile")
  UserDto getProfile(@Parameter(hidden = true) @AuthenticationPrincipal AuthenticatedUser user);
}
