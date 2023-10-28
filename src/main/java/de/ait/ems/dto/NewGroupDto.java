package de.ait.ems.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * 14/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@Data
@Schema(name = "New group")
public class NewGroupDto {

  @Schema(description = "Group name", example = "Cohort-26")
  @NotNull(message = "Must not be null")
  @NotBlank(message = "Must not be blank")
  @NotEmpty(message = "Must not be empty")
  @Size(max = 50, message = "Size must be in the range from 0 to 50")
  private String name;
  @Schema(description = "Course ID", example = "1")
  @NotNull(message = "Must not be null")
  @Min(value = 1, message = "Can't be zero or negative")
  private Long courseId;
}
