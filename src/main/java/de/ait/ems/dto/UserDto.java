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
  @Schema(description = "Username", example = "max.musterman")
  private String username;
  @Schema(description = "Password", example = "qwerty007")
  private String password;
  @Schema(description = "e-mail", example = "m.musterman@gmx.de")
  private String email;
  @Schema(description = "is blocked", example = "false")
  private boolean is_blocked;

  public static UserDto from(User user) {
    return UserDto.builder()
        .id(user.getId())
        .username(user.getUsername())
        .password(user.getPassword())
        .email(user.getEmail())
        .is_blocked(user.isBlocked())
        .build();
  }

  public static List<UserDto> from(List<User> users) {
    return users
        .stream()
        .map(UserDto::from)
        .collect(Collectors.toList());
  }
}
