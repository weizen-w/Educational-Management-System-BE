package de.ait.ems.dto;

import de.ait.ems.models.Submission.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for {@link de.ait.ems.models.Submission}
 *
 * @author Oleksandr Zhurba on 01.11.2023.
 * @project Educational-Management-System-BE
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Update submission", description = "Data for updating the submission")
public class UpdateSubmissionDto implements Serializable {

  @NotNull(message = "Must be not null")
  @NotBlank(message = "Description must not be blank")
  @NotEmpty(message = "Description must not be empty")
  @Schema(description = "Description", example = "Homework 'calculation of the area of a triangle'")
  String description;
  @NotNull(message = "Must be not null")
  @Schema(description = "Submission state", example = "VIEWED")
  Status submission_state;
  @NotNull(message = "Must be not null")
  @Schema(description = "Submission is archived", example = "false")
  Boolean archived;
}
