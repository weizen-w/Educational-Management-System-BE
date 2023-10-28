package de.ait.ems.repositories;

import de.ait.ems.models.ConfirmationCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * 26/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
public interface ConfirmationCodesRepository extends JpaRepository<ConfirmationCode, Long> {
  Optional<ConfirmationCode> findByCodeAndExpiredDateTimeAfter(String code, LocalDateTime now);
}
