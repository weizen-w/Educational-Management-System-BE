package de.ait.ems.controllers;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import de.ait.ems.config.TestSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
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

/**
 * 01/11/2023 EducationalManagementSystemBE
 *
 * @author Wladimir Weizen
 */
@SpringBootTest(classes = TestSecurityConfig.class)
@AutoConfigureMockMvc
@DisplayName("Endpoint /modules is works:")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class ModulesIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Nested
  @DisplayName("GET /modules:")
  public class GetModules {

    @Test
    @WithUserDetails("admin@gmail.com")
    public void return_empty_list_of_modules_for_empty_database() throws Exception {
      mockMvc.perform(get("/api/modules"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.size()", is(0)));
    }

    @Test
    @WithUserDetails("admin@gmail.com")
    @Sql(scripts = "/sql/data.sql")
    public void return_list_of_modules_for_not_empty_database() throws Exception {
      mockMvc.perform(get("/api/modules"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.size()", is(4)))
          .andExpect(jsonPath("$.[0].id", is(1)))
          .andExpect(jsonPath("$.[1].id", is(2)))
          .andExpect(jsonPath("$.[2].id", is(3)))
          .andExpect(jsonPath("$.[3].id", is(4)))
          .andExpect(jsonPath("$.[0].name", is("Module 1")))
          .andExpect(jsonPath("$.[1].name", is("Module 2")))
          .andExpect(jsonPath("$.[2].name", is("Module 3")))
          .andExpect(jsonPath("$.[3].name", is("Module 4")));
    }
  }

  @Nested
  @DisplayName("POST /modules:")
  public class PostModules {

    @Test
    @WithUserDetails("admin@gmail.com")
    @Sql(scripts = "/sql/data.sql")
    public void return_created_module() throws Exception {
      mockMvc.perform(post("/api/modules")
              .contentType(MediaType.APPLICATION_JSON)
              .content("""
                  {
                    "name": "New Module"
                  }"""))
          .andExpect(status().isCreated())
          .andExpect(jsonPath("$.id", is(5)));
    }

    @Test
    @WithUserDetails("admin@gmail.com")
    public void return_400_for_not_valid_module() throws Exception {
      mockMvc.perform(post("/api/modules")
              .contentType("application/json")
              .content("""
                  {
                    "name": " "
                  }"""))
          .andExpect(status().isBadRequest())
          .andExpect(jsonPath("$.errors.size()", is(1)));
    }
  }

  @Nested
  @DisplayName("PUT /modules/{module-id}:")
  public class PutModule {

    @Test
    @WithUserDetails("admin@gmail.com")
    @Sql(scripts = "/sql/data.sql")
    public void return_updated_module() throws Exception {
      mockMvc.perform(put("/api/modules/1")
              .contentType("application/json")
              .content("""
                  {
                    "name": "Update module",
                    "archived": true
                  }"""))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.id", is(1)))
          .andExpect(jsonPath("$.name", is("Update module")))
          .andExpect((jsonPath("$.archived", is(true))));
    }

    @Test
    @WithUserDetails("admin@gmail.com")
    public void return_404_for_not_existed_module() throws Exception {
      mockMvc.perform(put("/api/modules/5")
              .contentType("application/json")
              .content("""
                  {
                    "name": "Update module",
                    "archived": true
                  }"""))
          .andExpect(status().isNotFound());
    }

    @Test
    @WithUserDetails("admin@gmail.com")
    @Sql(scripts = "/sql/data.sql")
    public void return_400_for_not_valid_update_module() throws Exception {
      mockMvc.perform(put("/api/modules/1")
              .contentType("application/json")
              .content("""
                  {
                    "name": " ",
                    "archived": true
                  }"""))
          .andExpect(status().isBadRequest())
          .andExpect(jsonPath("$.errors.size()", is(1)));
    }
  }
}
