package de.ait.ems.services;

import de.ait.ems.dto.CourseDto;
import de.ait.ems.dto.NewCourseDto;
import de.ait.ems.dto.UpdateCourseDto;
import java.util.List;

/**
 * 11/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
public interface CoursesService {

  CourseDto addCourse(NewCourseDto newCourse);

  List<CourseDto> getCourses();

  CourseDto getCourse(Long courseId);

  CourseDto updateCourse(Long courseId, UpdateCourseDto updateCourse);

  CourseDto deleteCourse(Long courseId);

}
