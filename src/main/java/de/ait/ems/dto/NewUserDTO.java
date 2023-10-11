package de.ait.ems.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * @project EducationalManagementSystem
 * @AUTHOR Oleksandr Zhurba on 11.10.2023.
 **/
@Data
@Schema(name = "New user")
public class NewUserDTO {

  @Schema(description = "Username", example = "max.musterman")
  @NotNull
  @NotBlank
  @NotEmpty
  @Size(max = 100)
  private String username;
  private String password;
  private String email;
}
