package de.ait.ems.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Pattern;
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

  @Pattern(regexp = "^$|^(?!\\s+$).+", message = "Must not be blank or contain only spaces")
  @Size(min = 1, max = 200, message = "Size must be in the range from 1 to 200")
  @Schema(description = "Course name", example = "Fullstack developer")
  private String name;
  @Schema(description = "Course is archived", example = "true")
  private Boolean archived;
}
