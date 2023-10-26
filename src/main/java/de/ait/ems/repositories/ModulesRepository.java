package de.ait.ems.repositories;

import de.ait.ems.models.Module;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 18/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
public interface ModulesRepository extends JpaRepository<Module, Long> {

}
