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
@Schema(name = "Course(direction)", description = "Course Description")
public class CourseDTO {

  @Schema(description = "Course ID", example = "1")
  private Long id;
  @Schema(description = "Course name", example = "Fullstack developer")
  private String name;

  public static CourseDTO from(Course course) {
    return CourseDTO.builder()
        .id(course.getId())
        .name(course.getName())
        .build();
  }

  public static List<CourseDTO> from(List<Course> courses) {
    return courses
        .stream()
        .map(CourseDTO::from)
        .collect(Collectors.toList());
  }
}
