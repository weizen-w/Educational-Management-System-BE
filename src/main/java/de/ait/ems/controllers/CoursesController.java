package de.ait.ems.controllers;

import de.ait.ems.controllers.api.CoursesApi;
import de.ait.ems.dto.CourseDto;
import de.ait.ems.dto.NewCourseDto;
import de.ait.ems.dto.UpdateCourseDto;
import de.ait.ems.services.CoursesService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 08/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@RestController
@RequiredArgsConstructor
public class CoursesController implements CoursesApi {

  private final CoursesService coursesService;

  @Override
  public CourseDto addCourse(NewCourseDto newCourse) {
    return coursesService.addCourse(newCourse);
  }

  @Override
  public List<CourseDto> getCourses() {
    return coursesService.getCourses();
  }

  @Override
  public CourseDto getCourse(Long courseId) {
    return coursesService.getCourse(courseId);
  }

  @Override
  public CourseDto updateCourse(Long courseId, UpdateCourseDto updateCourse) {
    return coursesService.updateCourse(courseId, updateCourse);
  }
}
