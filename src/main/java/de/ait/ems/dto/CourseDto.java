package de.ait.ems.dto;

import de.ait.ems.models.Course;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 08/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "Course(direction)", description = "Course description")
public class CourseDto {

  @Schema(description = "Course ID", example = "1")
  private Long id;
  @Schema(description = "Course name", example = "Fullstack developer")
  private String name;
  @Schema(description = "Course is archived", example = "false")
  private Boolean isArchived;

  public static CourseDto from(Course course) {
    return CourseDto.builder()
        .id(course.getId())
        .name(course.getName())
        .isArchived(course.getIsArchived())
        .build();
  }

  public static List<CourseDto> from(List<Course> courses) {
    return courses
        .stream()
        .map(CourseDto::from)
        .collect(Collectors.toList());
  }
}
