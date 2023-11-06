package de.ait.ems.controllers.api;

import de.ait.ems.dto.AttendanceDto;
import de.ait.ems.dto.GroupDto;
import de.ait.ems.dto.NewUserDto;
import de.ait.ems.dto.StandardResponseDto;
import de.ait.ems.dto.UpdateUserDto;
import de.ait.ems.dto.UserDto;
import de.ait.ems.models.User.Role;
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
import javax.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 17/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@RequestMapping("/api/users")
@Tags(value = {@Tag(name = "Users")})
public interface UsersApi {

  @GetMapping
  @PreAuthorize("hasAuthority('ADMIN')")
  @Operation(summary = "Get all users", description = "Return list of all users. Available to administrator")
  @ResponseStatus(code = HttpStatus.OK)
  List<UserDto> getAllUsers();

  @GetMapping("/byRole/{role}")
  @PreAuthorize("hasAuthority('ADMIN')")
  @Operation(summary = "Get users by role", description = "Return list of users by role. Available to administrator")
  List<UserDto> getUsersByRole(@PathVariable Role role);

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

  @Operation(summary = "Update user by ID", description = "Update user info. Available to administrator")
  @PreAuthorize("hasAuthority('ADMIN')")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "Update processed successfully",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = GroupDto.class))
      ),
      @ApiResponse(responseCode = "400",
          description = "Validation error",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = ValidationErrorsDto.class))
      ),
      @ApiResponse(responseCode = "404",
          description = "Group not found",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = StandardResponseDto.class)))
  })
  @PutMapping("/{user-id}")
  UserDto updateUser(
      @Parameter(description = "User ID", example = "1", required = true) @PathVariable("user-id") @Min(1) Long userId,
      @RequestBody @Valid UpdateUserDto updateUser);

  @GetMapping("/{user-id}/attendance")
  @PreAuthorize("hasAuthority('ADMIN')")
  @Operation(summary = "Get attendance by user id", description = "Return list of attendances of selected user. Available to administrator")
  @ResponseStatus(code = HttpStatus.OK)
  List<AttendanceDto> getAttendanceByUserId(@Parameter(description = "User ID", example = "1", required = true) @PathVariable("user-id") @Min(1) Long userId);

}
