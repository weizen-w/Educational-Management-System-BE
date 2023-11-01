package de.ait.ems.repositories;

import de.ait.ems.models.Module;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 01/11/2023 EducationalManagementSystemBE
 *
 * @author Wladimir Weizen
 */
public interface ModulesRepository extends JpaRepository<Module, Long> {

}
