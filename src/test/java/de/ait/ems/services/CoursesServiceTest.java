package de.ait.ems.services;

import de.ait.ems.dto.CourseDto;
import de.ait.ems.dto.CoursesDtoTest;
import de.ait.ems.dto.NewCourseDto;
import de.ait.ems.dto.UpdateCourseDto;
import de.ait.ems.exceptions.RestException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

/**
 * 13/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@SpringBootTest
@Nested
@DisplayName("Course service is works:")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class CoursesServiceTest {

  @Autowired
  private CoursesService coursesService;

  @Test
  @Sql(scripts = "/sql/data.sql")
  @Transactional
  public void add_course() {
    NewCourseDto newCourseDto = new NewCourseDto();
    newCourseDto.setName(CoursesDtoTest.NAME);
    CourseDto courseDto = coursesService.addCourse(newCourseDto);

    Assertions.assertNotNull(courseDto);
    Assertions.assertEquals(5, courseDto.getId());
    Assertions.assertEquals(newCourseDto.getName(), courseDto.getName());
    Assertions.assertEquals(false, courseDto.getArchived());
  }

  @Test
  @Sql(scripts = "/sql/data.sql")
  @Transactional
  public void get_course() {
    CourseDto courseDto = coursesService.getCourse(CoursesDtoTest.ID);

    Assertions.assertNotNull(courseDto);
    Assertions.assertEquals(CoursesDtoTest.ID, courseDto.getId());
    Assertions.assertEquals("Course 1", courseDto.getName());
    Assertions.assertEquals(false, courseDto.getArchived());
  }

  @Test
  @Sql(scripts = "/sql/data.sql")
  @Transactional
  public void get_courses() {
    List<CourseDto> courses = coursesService.getCourses();

    Assertions.assertNotNull(courses);
    Assertions.assertEquals(4, courses.size());
  }

  @Test
  @Sql(scripts = "/sql/data.sql")
  @Transactional
  public void update_course() {
    UpdateCourseDto updateCourseDto = new UpdateCourseDto(CoursesDtoTest.NAME,
        CoursesDtoTest.IS_ARCHIVED);
    CourseDto courseDtoAfterUpdate = coursesService.updateCourse(CoursesDtoTest.ID,
        updateCourseDto);

    Assertions.assertNotNull(courseDtoAfterUpdate);
    Assertions.assertEquals(CoursesDtoTest.ID, courseDtoAfterUpdate.getId());
    Assertions.assertEquals(updateCourseDto.getName(), courseDtoAfterUpdate.getName());
    Assertions.assertEquals(updateCourseDto.getArchived(), courseDtoAfterUpdate.getArchived());
  }

  @Test
  @Sql(scripts = "/sql/data.sql")
  @Transactional
  public void get_exception_for_not_exist_course() {
    Long notExistCourseId = 999L;

    Assertions.assertThrows(RestException.class, () -> coursesService.getCourse(notExistCourseId));
  }
}
