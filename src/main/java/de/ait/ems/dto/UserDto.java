package de.ait.ems.dto;

import de.ait.ems.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project EducationalManagementSystem
 * @AUTHOR Oleksandr Zhurba on 11.10.2023.
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "User", description = "User Description")
public class UserDto {

  @Schema(description = "User ID", example = "1")
  private Long id;
  @Schema(description = "User password", example = "qwerty007")
  private String password;
  @Schema(description = "User first name", example = "Max")
  private String firstName;
  @Schema(description = "User last name", example = "Musterman")
  private String lastName;
  @Schema(description = "e-mail", example = "m.musterman@gmx.de")
  private String email;
  @Schema(description = "User role", example = "STUDENT")
  private String role;
  @Schema(description = "User state", example = "CONFIRMED")
  private String state;
  @Schema(description = "User photo")
  private String photoLink;

  public static UserDto from(User user) {
    return UserDto.builder()
        .id(user.getId())
        .password(user.getHashPassword())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .email(user.getEmail())
        .role(user.getRole().toString())
        .state(user.getState().name())
        .photoLink(user.getPhotoLink())
        .build();
  }

  public static List<UserDto> from(List<User> users) {
    return users.stream()
        .map(UserDto::from)
        .collect(Collectors.toList());
  }
}
