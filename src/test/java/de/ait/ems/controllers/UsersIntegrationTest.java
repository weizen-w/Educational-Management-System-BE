package de.ait.ems.controllers;

import de.ait.ems.config.TestSecurityConfig;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 26/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@SpringBootTest(classes = TestSecurityConfig.class)
@AutoConfigureMockMvc
@DisplayName("Endpoint /users is works:")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class UsersIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Nested
  @DisplayName("POST /users/register:")
  public class RegisterUser {

    @Test
    @Transactional
    public void return_created_user() throws Exception {
      mockMvc.perform(post("/api/users/register")
              .contentType(MediaType.APPLICATION_JSON)
              .content("""
                  {
                    "email": "test1@gmx.de",
                    "password": "Qwerty007!",
                    "firstName": "Test",
                    "lastName": "Test"
                  }"""))
          .andExpect(status().isCreated())
          .andExpect(jsonPath("$.id", is(1)))
          .andExpect(jsonPath("$.role", is("STUDENT")));
    }

    @Test
    @Transactional
    public void return_400_for_bad_format_email() throws Exception {
      mockMvc.perform(post("/api/users/register")
              .contentType(MediaType.APPLICATION_JSON)
              .content("""
                  {
                    "email": "test1gmxde",
                    "password": "Qwerty007!",
                    "firstName": "Test",
                    "lastName": "Test"
                  }"""))
          .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(scripts = "/sql/data.sql")
    @Transactional
    public void return_409_for_existed_email() throws Exception {
      mockMvc.perform(post("/api/users/register")
              .contentType(MediaType.APPLICATION_JSON)
              .content("""
                  {
                    "email": "Student1@gmail.com",
                    "password": "Qwerty007!",
                    "firstName": "Student1",
                    "lastName": "Name1"
                  }"""))
          .andExpect(status().isConflict());
    }
  }

  @Nested
  @DisplayName("GET /users/profile")
  public class GetProfile {

    @Test
    @Transactional
    public void return_403_for_unauthorized() throws Exception {
      mockMvc.perform(get("/api/users/profile"))
          .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails("student1@gmail.com")
    @Sql(scripts = "/sql/data.sql")
    @Transactional
    public void return_information_about_current_user() throws Exception {
      mockMvc.perform(get("/api/users/profile"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.id", is(1)))
          .andExpect(jsonPath("$.role", is("STUDENT")));
    }
  }
}
