package de.ait.ems.dto;

import de.ait.ems.models.Submission;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for {@link de.ait.ems.models.Submission}
 * @author Oleksandr Zhurba on 01.11.2023.
 * @project Educational-Management-System-BE
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmissionDto implements Serializable {

  @NotNull(message = "Must be not null")
  @Min(1L)
  @Schema(description = "Submission ID", example = "1")
  Long submission_id;
  @NotNull(message = "Must be not null")
  @Pattern(message = "Must not be blank or contain only spaces", regexp = "^$|^(?!\\\\s+$).+")
  @Schema(description = "Description", example = "Homework 'calculation of the area of a triangle'")
  String description;
  @Schema(description = "Lesson ID", example = "1")
  @NotNull(message = "Must be not null")
  Long lesson_id;
  @Schema(description = "Student ID", example = "1")
  @NotNull(message = "Must be not null")
  Long student_id;
  @NotNull(message = "Must be not null")
  @Schema(description = "Submission state", example = "VIEWED")
  String submission_state;
  @NotNull(message = "Must be not null")
  @Schema(description = "Submission is archived", example = "false")
  Boolean archived;

  public static SubmissionDto from(Submission submission) {
    return SubmissionDto.builder()
        .submission_id(submission.getId())
        .description(submission.getDescription())
        .lesson_id(submission.getLesson().getId())
        .student_id(submission.getStudent().getId())
        .submission_state(submission.getState().name())
        .archived(submission.getArchived())
        .build();
  }

  public static List<SubmissionDto> from(List<Submission> submission) {
    return submission.stream()
        .map(SubmissionDto::from)
        .collect(Collectors.toList());
  }
}