package de.ait.ems.config;

import de.ait.ems.models.User;
import de.ait.ems.models.User.Role;
import de.ait.ems.models.User.State;
import de.ait.ems.security.details.AuthenticatedUser;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * 26/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@TestConfiguration
@Profile("test")
public class TestSecurityConfig {

  public static final String MOCK_STUDENT = "student1@gmail.com";

  public static final String MOCK_ADMIN = "admin@gmail.com";

  @Bean
  @Primary
  public UserDetailsService userDetailsService() {
    return new InMemoryUserDetailsManager() {
      @Override
      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals(MOCK_STUDENT)) {
          return new AuthenticatedUser(User.builder()
              .id(1L)
              .email(MOCK_STUDENT)
              .role(Role.STUDENT)
              .state(State.CONFIRMED)
              .build());
        } else if (username.equals(MOCK_ADMIN)) {
          return new AuthenticatedUser(User.builder()
              .id(1L)
              .email(MOCK_ADMIN)
              .role(User.Role.ADMIN)
              .state(State.CONFIRMED)
              .build());
        } else throw new UsernameNotFoundException("User not found");
      }
    };
  }
}
