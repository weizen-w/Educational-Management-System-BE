package de.ait.ems.services.impl;

import static de.ait.ems.dto.CourseDto.from;

import de.ait.ems.dto.CourseDto;
import de.ait.ems.dto.NewCourseDto;
import de.ait.ems.dto.UpdateCourseDto;
import de.ait.ems.exceptions.RestException;
import de.ait.ems.models.Course;
import de.ait.ems.repositories.CoursesRepository;
import de.ait.ems.services.CoursesService;
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
public class CoursesServiceImpl implements CoursesService {

  private final CoursesRepository coursesRepository;

  @Override
  public CourseDto addCourse(NewCourseDto newCourse) {
    Course course = Course.builder()
        .name(newCourse.getName())
        .isArchived(false)
        .build();
    coursesRepository.save(course);
    return from(course);
  }

  @Override
  public List<CourseDto> getCourses() {
    List<Course> courses = coursesRepository.findAll();
    return from(courses);
  }

  @Override
  public CourseDto getCourse(Long courseId) {
    Course course = getCourseOrThrow(courseId);
    return from(course);
  }

  @Override
  public CourseDto updateCourse(Long courseId, UpdateCourseDto updateCourse) {
    Course courseForUpdate = getCourseOrThrow(courseId);
    courseForUpdate.setName(updateCourse.getName());
    if (updateCourse.getIsArchived() != null) {
      courseForUpdate.setIsArchived(updateCourse.getIsArchived());
    }
    coursesRepository.save(courseForUpdate);
    return from(courseForUpdate);
  }

  @Override
  public CourseDto deleteCourse(Long courseId) {
    Course courseForDelete = getCourseOrThrow(courseId);
    coursesRepository.delete(courseForDelete);
    return from(courseForDelete);
  }

  private Course getCourseOrThrow(Long courseId) {
    return coursesRepository.findById(courseId).orElseThrow(
        () -> new RestException(HttpStatus.NOT_FOUND,
            "Course with id <" + courseId + "> not found"));
  }
}
