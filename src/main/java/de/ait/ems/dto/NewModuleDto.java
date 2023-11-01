package de.ait.ems.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * 01/11/2023 EducationalManagementSystemBE
 *
 * @author Wladimir Weizen
 */
@Data
@Schema(name = "New module")
public class NewModuleDto {

  @Schema(description = "Module name", example = "Backend")
  @NotNull(message = "Must not be null")
  @NotBlank(message = "Must not be blank")
  @NotEmpty(message = "Must not be empty")
  @Size(max = 50, message = "Size must be in the range from 0 to 50")
  private String name;
}
