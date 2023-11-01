package de.ait.ems.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 01/11/2023 EducationalManagementSystemBE
 *
 * @author Wladimir Weizen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Update module", description = "Data for updating the module")
public class UpdateModuleDto {

  @Pattern(regexp = "^$|^(?!\\s+$).+", message = "Must not be blank or contain only spaces")
  @Size(min = 1, max = 50, message = "Size must be in the range from 1 to 50")
  @Schema(description = "Module name", example = "Backend")
  private String name;
  @Schema(description = "Module is archived", example = "true")
  private Boolean archived;
}
