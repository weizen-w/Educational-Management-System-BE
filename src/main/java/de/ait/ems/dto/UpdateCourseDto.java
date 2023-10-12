package de.ait.ems.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 11/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Update course", description = "Data for updating the course")
public class UpdateCourseDto {

  @NotNull(message = "Must not be null")
  @NotBlank(message = "Must not be blank")
  @NotEmpty(message = "Must not be empty")
  @Size(max = 100, message = "Size must be in the range from 0 to 100")
  @Schema(description = "Course name", example = "Fullstack developer")
  private String name;
  @Schema(description = "Course is archived", example = "true")
  private Boolean isArchived;
}
