package de.ait.ems.repositories;

import de.ait.ems.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 18/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
public interface LessonsRepository extends JpaRepository<Lesson, Long> {

}
