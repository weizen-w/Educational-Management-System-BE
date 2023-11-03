package de.ait.ems.repositories;

import de.ait.ems.models.ConfirmationCode;
import de.ait.ems.models.User;
import de.ait.ems.models.User.Role;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @project EducationalManagementSystem
 * @AUTHOR Oleksandr Zhurba on 11.10.2023.
 **/
public interface UsersRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  boolean existsByEmail(String email);

  Optional<User> findFirstByCodesContains(ConfirmationCode code);

  List<User> findAllByRole(Role role);
}
