package de.ait.ems.services;

import static de.ait.ems.dto.CourseDto.from;

import de.ait.ems.dto.CourseDto;
import de.ait.ems.dto.NewCourseDto;
import de.ait.ems.dto.UpdateCourseDto;
import de.ait.ems.exceptions.RestException;
import de.ait.ems.models.Course;
import de.ait.ems.repositories.CoursesRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * 08/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@RequiredArgsConstructor
@Service
public class CoursesService {

  private final CoursesRepository coursesRepository;

  public CourseDto addCourse(NewCourseDto newCourse) {
    Course course = Course.builder()
        .name(newCourse.getName())
        .archived(false)
        .build();
    coursesRepository.save(course);
    return from(course);
  }

  public List<CourseDto> getCourses() {
    List<Course> courses = coursesRepository.findAll();
    return from(courses);
  }

  public CourseDto getCourse(Long courseId) {
    Course course = getCourseOrThrow(courseId);
    return from(course);
  }

  public CourseDto updateCourse(Long courseId, UpdateCourseDto updateCourse) {
    Course courseForUpdate = getCourseOrThrow(courseId);
    if (updateCourse.getName() != null) {
      courseForUpdate.setName(updateCourse.getName());
    }
    if (updateCourse.getArchived() != null) {
      courseForUpdate.setArchived(updateCourse.getArchived());
    }
    coursesRepository.save(courseForUpdate);
    return from(courseForUpdate);
  }

  private Course getCourseOrThrow(Long courseId) {
    return coursesRepository.findById(courseId).orElseThrow(
        () -> new RestException(HttpStatus.NOT_FOUND,
            "Course with id <" + courseId + "> not found"));
  }
}
