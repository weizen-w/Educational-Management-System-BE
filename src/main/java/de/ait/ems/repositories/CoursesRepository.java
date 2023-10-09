package de.ait.ems.repositories;

import de.ait.ems.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 08/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
public interface CoursesRepository extends JpaRepository<Course, Long> {

}
