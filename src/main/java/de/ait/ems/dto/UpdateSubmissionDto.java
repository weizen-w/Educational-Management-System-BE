package de.ait.ems.dto;

import de.ait.ems.models.Submission.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Value;

/**
 * DTO for {@link de.ait.ems.models.Submission}
 * @author Oleksandr Zhurba on 01.11.2023.
 * @project Educational-Management-System-BE
 */
@Value
public class UpdateSubmissionDto implements Serializable {

  @NotNull(message = "Must be not null")
  @Pattern(message = "Must not be blank or contain only spaces", regexp = "^$|^(?!\\\\s+$).+")
  @Schema(description = "Description", example = "Homework 'calculation of the area of a triangle'")
  String description;
  @NotNull(message = "Must be not null")
  @Schema(description = "Submission state", example = "VIEWED")
  Status state;
  @NotNull(message = "Must be not null")
  @Schema(description = "Submission is archived", example = "false")
  Boolean archived;
}
