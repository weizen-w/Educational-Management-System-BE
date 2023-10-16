package de.ait.ems.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 14/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Update group", description = "Data for updating the group")
public class UpdateGroupDto {

  @Pattern(regexp = "^$|^(?!\\s+$).+", message = "Must not be blank or contain only spaces")
  @Size(min = 1, max = 150, message = "Size must be in the range from 1 to 150")
  @Schema(description = "Group name", example = "Cohort-26")
  private String name;
  @Min(value = 1, message = "Can't be zero or negative")
  @Schema(description = "Group course ID", example = "1")
  private Long courseId;
  @Schema(description = "Group is archived", example = "true")
  private Boolean isArchived;
}
