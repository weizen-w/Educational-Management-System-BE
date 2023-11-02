package de.ait.ems.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Oleksandr Zhurba on 01.11.2023.
 * @project Educational-Management-System-BE
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "New lesson", description = "Adding new Lesson for students and teachers")
public class UpdateLessonDto {

  @Schema(description = "Title", example = "basic_programming/lesson_52")
  @NotNull(message = "Must be not null")
  @NotBlank(message = "Must be not blank")
  @NotEmpty(message = "Must be not empty")
  private String lessonTitle;
  @Schema(description = "Description", example = "\n"
      + "    Smalltalk about homework. Questions.\n"
      + "    Binary seek in arrays\n"
      + "    Binary seek by answer: collaboration task\n"
      + "\n")
  @NotNull(message = "Must be not null")
  private String lessonDescription;
  @NotNull(message = "Must be not null")
  @NotBlank(message = "Must be not blank")
  @NotEmpty(message = "Must be not empty")
  @Schema(description = "Lesson type", example = "consultation")
  private String lessonType;
  @Schema(description = "Teacher", implementation = UserDto.class)
  @NotNull(message = "Must be not null")
  @NotBlank(message = "Must be not blank")
  @NotEmpty(message = "Must be not empty")
  private UserDto teacher;
  @Schema(description = "Date", example = "2023-11-27")
  @NotNull(message = "Must be not null")
  @NotBlank(message = "Must be not blank")
  @NotEmpty(message = "Must be not empty")
  private LocalDate lessonDate;
  @Schema(description = "Start time", example = "10:15:30")
  @NotNull(message = "Must be not null")
  @NotBlank(message = "Must be not blank")
  @NotEmpty(message = "Must be not empty")
  private LocalTime startTime;
  @Schema(description = "End time", example = "12:15:30")
  @NotNull(message = "Must be not null")
  @NotBlank(message = "Must be not blank")
  @NotEmpty(message = "Must be not empty")
  private LocalTime endTime;
  @Schema(description = "Module", implementation = ModuleDto.class)
  @NotNull(message = "Must be not null")
  @NotBlank(message = "Must be not blank")
  @NotEmpty(message = "Must be not empty")
  private ModuleDto module;
  @Schema(description = "Link LMS", example = "https://lms.ait-tr.de/#/group/cohort26/module/basic_programming/lesson/lesson_52")
  @NotNull(message = "Must be not null")
  @NotBlank(message = "Must be not blank")
  @NotEmpty(message = "Must be not empty")
  private String linkLms;
  @Schema(description = "Link Zoom", example = "https://us05web.zoom.us/j/82073564366?pwd=LqOp9ojsiqseacSVwFPyt2jZA2tqUI.1")
  @NotNull(message = "Must be not null")
  @NotBlank(message = "Must be not blank")
  @NotEmpty(message = "Must be not empty")
  private String linkZoom;
  @Schema(description = "Archived", example = "false")
  @NotNull(message = "Must be not null")
  private Boolean archived;
}
