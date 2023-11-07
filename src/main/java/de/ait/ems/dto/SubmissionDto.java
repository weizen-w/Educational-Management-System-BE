package de.ait.ems.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

/**
 * DTO for {@link de.ait.ems.models.Submission}
 * @author Oleksandr Zhurba on 01.11.2023.
 * @project Educational-Management-System-BE
 */
@Value
public class SubmissionDto implements Serializable {

  @NotNull(message = "Must be not null")
  @Min(1L)
  @Schema(description = "Submission ID", example = "1")
  Long id;
  @NotNull(message = "Must be not null")
  @Pattern(message = "Must not be blank or contain only spaces", regexp = "^$|^(?!\\\\s+$).+")
  @Schema(description = "Description", example = "Homework 'calculation of the area of a triangle'")
  String description;
  @Schema(description = "Lesson ID", example = "1")
  @NotNull(message = "Must be not null")
  Long lessonId;
  @Schema(description = "Student ID", example = "1")
  @NotNull(message = "Must be not null")
  Long studentId;
  @NotNull(message = "Must be not null")
  @Pattern(message = "Must not be blank or contain only spaces", regexp = "^$|^(?!\\\\s+$).+")
  @Length(message = "Size must be in the range from 1 to 20", min = 1, max = 20)
  @Schema(description = "Submission state", example = "VIEWED")
  String state;
  @NotNull(message = "Must be not null")
  @Schema(description = "Submission is archived", example = "false")
  Boolean archived;
}