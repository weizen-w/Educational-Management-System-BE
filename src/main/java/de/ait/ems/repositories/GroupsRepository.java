package de.ait.ems.repositories;

import de.ait.ems.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 14/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
public interface GroupsRepository extends JpaRepository<Group, Long> {

}
