package de.ait.ems.repositories;

import de.ait.ems.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @project EducationalManagementSystem
 * @AUTHOR Oleksandr Zhurba on 11.10.2023.
 **/
public interface UsersRepository extends JpaRepository<User, Long> {

}
