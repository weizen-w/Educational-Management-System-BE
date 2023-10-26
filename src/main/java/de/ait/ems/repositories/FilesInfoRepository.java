package de.ait.ems.repositories;

import de.ait.ems.models.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 26/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
public interface FilesInfoRepository extends JpaRepository<FileInfo, Long> {

}
