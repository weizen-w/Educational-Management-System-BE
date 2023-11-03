package de.ait.ems.dto;

import de.ait.ems.models.User;
import de.ait.ems.models.User.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 03/11/2023 EducationalManagementSystemBE
 *
 * @author Wladimir Weizen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Update user", description = "Data for updating the user")
public class UpdateUserDto {

  @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$")
  @Size(min = 1, max = 100, message = "Size must be in the range from 1 to 100")
  @Schema(description = "User password", example = "Qwerty007!")
  private String password;
  @Pattern(regexp = "^$|^(?!\\s+$).+", message = "Must not be blank or contain only spaces")
  @Size(min = 1, max = 50, message = "Size must be in the range from 1 to 50")
  @Schema(description = "User first name", example = "Wladimir")
  private String firstName;
  @Pattern(regexp = "^$|^(?!\\s+$).+", message = "Must not be blank or contain only spaces")
  @Size(min = 1, max = 50, message = "Size must be in the range from 1 to 50")
  @Schema(description = "User last name", example = "Weizen")
  private String lastName;
  @Email
  @Pattern(regexp = "^$|^(?!\\s+$).+", message = "Must not be blank or contain only spaces")
  @Size(min = 1, max = 100, message = "Size must be in the range from 1 to 100")
  @Schema(description = "User email", example = "mail@gmail.com")
  private String email;
  @Schema(description = "User role", example = "STUDENT")
  @Enumerated(value = EnumType.STRING)
  private Role role;
  @Schema(description = "User state", example = "CONFIRMED")
  @Enumerated(value = EnumType.STRING)
  private User.State state;
  @Pattern(regexp = "^$|^(?!\\s+$).+", message = "Must not be blank or contain only spaces")
  @Size(min = 1, message = "Size cannot be less than 1")
  @Schema(description = "User photo link", example = "https://####.###.com/article/url/url-object.jpeg")
  private String photoLink;
}
