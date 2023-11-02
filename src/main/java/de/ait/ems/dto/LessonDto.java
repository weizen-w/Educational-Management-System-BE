package de.ait.ems.dto;

import de.ait.ems.models.Lesson;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
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
@Schema(name = "Lesson", description = "Lessons for students and teachers")
public class LessonDto {

  @Schema(description = "Lesson ID", example = "1")
  private Long lessonId;
  @Schema(description = "Title", example = "basic_programming/lesson_52")
  private String lessonTitle;
  @Schema(description = "Description", example = """

          Smalltalk about homework. Questions.
          Binary seek in arrays
          Binary seek by answer: collaboration task

      """)
  private String lessonDescription;
  @Schema(description = "Lesson type", example = "consultation")
  private String lessonType;
  @Schema(description = "Teacher", implementation = UserDto.class)
  private UserDto teacher;
  @Schema(description = "Date", example = "2023-11-27")
  private LocalDate lessonDate;
  @Schema(description = "Start time", example = "10:15:30")
  private LocalTime startTime;
  @Schema(description = "End time", example = "12:15:30")
  private LocalTime endTime;
  @Schema(description = "Module", implementation = ModuleDto.class)
  private ModuleDto module;
  @Schema(description = "Link LMS", example = "https://lms.ait-tr.de/#/group/cohort26/module/basic_programming/lesson/lesson_52")
  private String linkLms;
  @Schema(description = "Link Zoom", example = "https://us05web.zoom.us/j/82073564366?pwd=LqOp9ojsiqseacSVwFPyt2jZA2tqUI.1")
  private String linkZoom;
  @Schema(description = "Archived", example = "false")
  private Boolean archived;

  public static LessonDto from(Lesson lesson) {
    return LessonDto.builder()
        .lessonId(lesson.getId())
        .lessonTitle(lesson.getLessonTitle())
        .lessonDescription(lesson.getLessonDescription())
        .lessonType(lesson.getLessonType())
        .teacher(UserDto.from(lesson.getTeacher()))
        .lessonDate(lesson.getLessonDate())
        .startTime(lesson.getStartTime())
        .endTime(lesson.getEndTime())
        .module(ModuleDto.from(lesson.getModule()))
        .linkLms(lesson.getLinkLms())
        .linkZoom(lesson.getLinkZoom())
        .archived(lesson.getArchived())
        .build();
  }

  public static List<LessonDto> from(List<Lesson> lessons) {
    return lessons.stream()
        .map(LessonDto::from)
        .collect(Collectors.toList());
  }
}
