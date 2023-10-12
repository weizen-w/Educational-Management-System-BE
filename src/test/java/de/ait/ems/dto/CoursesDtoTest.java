package de.ait.ems.dto;

import de.ait.ems.models.Course;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * 12/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@DisplayName("Courses DTO is works:")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class CoursesDtoTest {

  public static final Long ID = 1L;
  public static final String NAME = "Fullstack developer";
  public static final Boolean IS_ARCHIVED = false;

  @Nested
  @DisplayName("CourseDto:")
  public class TestsCourseDto {

    @Test
    public void get_course_dto() {
      Course course = new Course(ID, NAME, IS_ARCHIVED);
      CourseDto courseDto = CourseDto.from(course);

      Assertions.assertEquals(ID, courseDto.getId());
      Assertions.assertEquals(NAME, courseDto.getName());
      Assertions.assertEquals(IS_ARCHIVED, courseDto.getIsArchived());
    }

    @Test
    public void get_courses_dto() {
      Course course1 = new Course(ID, NAME, IS_ARCHIVED);
      Course course2 = new Course(ID, NAME, IS_ARCHIVED);
      List<Course> courses = new ArrayList<>();
      courses.add(course1);
      courses.add(course2);
      List<CourseDto> courseDtoList = CourseDto.from(courses);

      Assertions.assertEquals(2, courseDtoList.size());
    }
  }

  @Nested
  @DisplayName("NewCourseDto:")
  public class TestsNewCourseDto {

    @Test
    public void get_new_course_dto() {
      NewCourseDto newCourseDto = new NewCourseDto();
      newCourseDto.setName(NAME);

      Assertions.assertEquals(NAME, newCourseDto.getName());
    }
  }

  @Nested
  @DisplayName("UpdateCourseDto:")
  public class TestsUpdateCourseDto {

    @Test
    public void get_update_course_dto() {
      UpdateCourseDto updateCourseDto = new UpdateCourseDto();
      updateCourseDto.setName(NAME);
      updateCourseDto.setIsArchived(IS_ARCHIVED);

      Assertions.assertEquals(NAME, updateCourseDto.getName());
      Assertions.assertEquals(IS_ARCHIVED, updateCourseDto.getIsArchived());
    }
  }
}
