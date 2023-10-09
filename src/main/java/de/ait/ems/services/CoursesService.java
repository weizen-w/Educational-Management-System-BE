package de.ait.ems.services;

import static de.ait.ems.dto.CourseDTO.from;

import de.ait.ems.dto.CourseDTO;
import de.ait.ems.dto.NewCourseDTO;
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

  public CourseDTO addCourse(NewCourseDTO newCourseDTO) {
    Course course = Course.builder()
        .name(newCourseDTO.getName())
        .build();
    coursesRepository.save(course);
    return from(course);
  }
}
