package de.ait.ems.controllers;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

/**
 * 14/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Endpoint /groups is works:")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("test")
public class GroupsControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Nested
  @DisplayName("GET /groups:")
  public class GetGroups {

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void return_empty_list_of_groups_for_empty_database() throws Exception {
      mockMvc.perform(get("/api/groups"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.size()", is(0)));
    }

    @Test
    @Sql(scripts = "/sql/data.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void return_list_of_groups_for_not_empty_database() throws Exception {
      mockMvc.perform(get("/api/groups"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.size()", is(4)))
          .andExpect(jsonPath("$.[0].id", is(1)))
          .andExpect(jsonPath("$.[1].id", is(2)))
          .andExpect(jsonPath("$.[2].id", is(3)))
          .andExpect(jsonPath("$.[3].id", is(4)))
          .andExpect(jsonPath("$.[0].name", is("Group 1")))
          .andExpect(jsonPath("$.[1].name", is("Group 2")))
          .andExpect(jsonPath("$.[2].name", is("Group 3")))
          .andExpect(jsonPath("$.[3].name", is("Group 4")));
    }
  }

  @Nested
  @DisplayName("POST /groups:")
  public class PostGroup {

    @Test
    @Sql(scripts = "/sql/data.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void return_created_group() throws Exception {
      mockMvc.perform(post("/api/groups")
              .contentType(MediaType.APPLICATION_JSON)
              .content("""
                  {
                    "name": "New Group",
                    "courseId": 1
                  }"""))
          .andExpect(status().isCreated())
          .andExpect(jsonPath("$.id", is(5)));
    }

    @Test
    public void return_400_for_not_valid_group() throws Exception {
      mockMvc.perform(post("/api/groups")
              .contentType("application/json")
              .content("""
                  {
                    "name": "",
                    "courseId": 0
                  }"""))
          .andExpect(status().isBadRequest())
          .andExpect(jsonPath("$.errors.size()", is(3)));
    }
  }

  @Nested
  @DisplayName("GET /groups/{group-id}:")
  public class GetGroup {

    @Test
    @Sql(scripts = "/sql/data.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void return_existed_group() throws Exception {
      mockMvc.perform(get("/api/groups/1"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.id", is(1)))
          .andExpect(jsonPath("$.name", is("Group 1")))
          .andExpect(jsonPath("$.courseId", is(1)))
          .andExpect((jsonPath("$.isArchived", is(false))));
    }

    @Test
    @Sql(scripts = "/sql/data.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void return_404_for_not_existed_group() throws Exception {
      mockMvc.perform(get("/api/groups/5"))
          .andExpect(status().isNotFound());
    }
  }

  @Nested
  @DisplayName("PUT /groups/{group-id}:")
  public class PutGroup {

    @Test
    @Sql(scripts = "/sql/data.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void return_updated_group() throws Exception {
      mockMvc.perform(put("/api/groups/1")
              .contentType("application/json")
              .content("""
                  {
                    "name": "Update group",
                    "courseId": 2,
                    "isArchived": true
                  }"""))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.id", is(1)))
          .andExpect(jsonPath("$.name", is("Update group")))
          .andExpect(jsonPath("$.courseId", is(2)))
          .andExpect((jsonPath("$.isArchived", is(true))));
    }

    @Test
    @Sql(scripts = "/sql/data.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void return_400_for_not_valid_update_group() throws Exception {
      mockMvc.perform(put("/api/groups/1")
              .contentType("application/json")
              .content("""
                  {
                    "name": "",
                    "courseId": 0,
                    "isArchived": true
                  }"""))
          .andExpect(status().isBadRequest())
          .andExpect(jsonPath("$.errors.size()", is(2)));
    }

    @Test
    @Sql(scripts = "/sql/data.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void return_404_for_not_existed_group() throws Exception {
      mockMvc.perform(put("/api/groups/5")
              .contentType("application/json")
              .content("""
                  {
                    "name": "Update group",
                    "courseId": 2,
                    "isArchived": true
                  }"""))
          .andExpect(status().isNotFound());
    }
  }

  @Nested
  @DisplayName("DELETE /groups/{group-id}:")
  public class DeleteGroup {

    @Test
    @Sql(scripts = "/sql/data.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void return_deleted_group() throws Exception {
      mockMvc.perform(delete("/api/groups/1"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.id", is(1)))
          .andExpect(jsonPath("$.name", is("Group 1")))
          .andExpect(jsonPath("$.courseId", is(1)))
          .andExpect((jsonPath("$.isArchived", is(false))));
    }

    @Test
    @Sql(scripts = "/sql/data.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void return_404_for_not_existed_group() throws Exception {
      mockMvc.perform(delete("/api/groups/5"))
          .andExpect(status().isNotFound());
    }
  }
}
