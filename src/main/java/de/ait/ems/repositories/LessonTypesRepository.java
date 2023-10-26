package de.ait.ems.repositories;

import de.ait.ems.models.LessonType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 18/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
public interface LessonTypesRepository extends JpaRepository<LessonType, Long> {

}
