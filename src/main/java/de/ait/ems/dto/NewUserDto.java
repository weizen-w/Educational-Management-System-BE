package de.ait.ems.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * @project EducationalManagementSystem
 * @AUTHOR Oleksandr Zhurba on 11.10.2023.
 **/
@Data
@Schema(name = "New user", description = "Registration details")
public class NewUserDto {

  @NotNull
  @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$")
  @Schema(description = "User password", example = "Qwerty007!")
  @Size(max = 100, message = "Size must be in the range from 0 to 100")
  private String password;
  @NotNull
  @Schema(description = "User first name", example = "Max")
  @Size(max = 50, message = "Size must be in the range from 0 to 50")
  private String firstName;
  @NotNull
  @Schema(description = "User last name", example = "Musterman")
  @Size(max = 50, message = "Size must be in the range from 0 to 50")
  private String lastName;
  @Email
  @NotNull
  @Schema(description = "User email", example = "user@mail.com")
  @Size(max = 100, message = "Size must be in the range from 0 to 100")
  private String email;
}
