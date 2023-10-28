package de.ait.ems.security.details;

import de.ait.ems.models.User;
import de.ait.ems.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 26/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UsersRepository usersRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = usersRepository.findByEmail(email).orElseThrow(
        () -> new UsernameNotFoundException("User with email <" + email + "> not found"));
    return new AuthenticatedUser(user);
  }
}
