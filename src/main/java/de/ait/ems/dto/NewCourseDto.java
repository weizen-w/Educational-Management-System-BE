package de.ait.ems.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * 08/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@Data
@Schema(name = "New course")
public class NewCourseDto {

  @Schema(description = "Course name", example = "Fullstack developer")
  @NotNull(message = "Must not be null")
  @NotBlank(message = "Must not be blank")
  @NotEmpty(message = "Must not be empty")
  @Size(max = 100, message = "Size must be in the range from 0 to 100")
  private String name;
}
