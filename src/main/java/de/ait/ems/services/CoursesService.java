package de.ait.ems.services;

import static de.ait.ems.dto.CourseDto.from;

import de.ait.ems.dto.CourseDto;
import de.ait.ems.dto.NewCourseDto;
import de.ait.ems.models.Course;
import de.ait.ems.repositories.CoursesRepository;
import lombok.RequiredArgsConstructor;
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

  public CourseDto addCourse(NewCourseDto newCourseDto) {
    Course course = Course.builder()
        .name(newCourseDto.getName())
        .build();
    coursesRepository.save(course);
    return from(course);
  }
}
