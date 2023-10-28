package de.ait.ems.repositories;

import de.ait.ems.models.UserGroup;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 27/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
public interface UserGroupsRepository extends JpaRepository<UserGroup, Long> {

  List<UserGroup> findByUserId(Long id);

  List<UserGroup> findByGroupId(Long groupId);
}
