package de.ait.ems.controllers;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 12/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Endpoint /courses is works:")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("test")
public class CoursesIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Nested
  @DisplayName("GET /courses:")
  public class GetCourses {

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void return_empty_list_of_courses_for_empty_database() throws Exception {
      mockMvc.perform(get("/api/courses"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.size()", is(0)));
    }

    @Test
    @Sql(scripts = "/sql/data.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void return_list_of_courses_for_not_empty_database() throws Exception {
      mockMvc.perform(get("/api/courses"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.size()", is(4)))
          .andExpect(jsonPath("$.[0].id", is(1)))
          .andExpect(jsonPath("$.[1].id", is(2)))
          .andExpect(jsonPath("$.[2].id", is(3)))
          .andExpect(jsonPath("$.[3].id", is(4)))
          .andExpect(jsonPath("$.[0].name", is("Course 1")))
          .andExpect(jsonPath("$.[1].name", is("Course 2")))
          .andExpect(jsonPath("$.[2].name", is("Course 3")))
          .andExpect(jsonPath("$.[3].name", is("Course 4")));
    }
  }

  @Nested
  @DisplayName("POST /courses:")
  public class PostCourse {

    @Test
    @Sql(scripts = "/sql/data.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void return_created_course() throws Exception {
      mockMvc.perform(post("/api/courses")
              .contentType(MediaType.APPLICATION_JSON)
              .content("""
                  {
                    "name": "New Course"
                  }"""))
          .andExpect(status().isCreated())
          .andExpect(jsonPath("$.id", is(5)));
    }

    @Test
    public void return_400_for_not_valid_course() throws Exception {
      mockMvc.perform(post("/api/courses")
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
  @DisplayName("GET /courses/{course-id}:")
  public class GetCourse {

    @Test
    @Sql(scripts = "/sql/data.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void return_existed_course() throws Exception {
      mockMvc.perform(get("/api/courses/1"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.id", is(1)))
          .andExpect(jsonPath("$.name", is("Course 1")))
          .andExpect((jsonPath("$.isArchived", is(false))));
    }

    @Test
    @Sql(scripts = "/sql/data.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void return_404_for_not_existed_course() throws Exception {
      mockMvc.perform(get("/api/courses/5"))
          .andExpect(status().isNotFound());
    }
  }

  @Nested
  @DisplayName("PUT /courses/{course-id}:")
  public class PutCourse {

    @Test
    @Sql(scripts = "/sql/data.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void return_updated_course() throws Exception {
      mockMvc.perform(put("/api/courses/1")
              .contentType("application/json")
              .content("""
                  {
                    "name": "Update course",
                    "isArchived": true
                  }"""))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.id", is(1)))
          .andExpect(jsonPath("$.name", is("Update course")))
          .andExpect((jsonPath("$.isArchived", is(true))));
    }

    @Test
    @Sql(scripts = "/sql/data.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void return_400_for_not_valid_update_course() throws Exception {
      mockMvc.perform(put("/api/courses/1")
              .contentType("application/json")
              .content("""
                  {
                    "name": " ",
                    "isArchived": true
                  }"""))
          .andExpect(status().isBadRequest())
          .andExpect(jsonPath("$.errors.size()", is(1)));
    }

    @Test
    @Sql(scripts = "/sql/data.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void return_404_for_not_existed_course() throws Exception {
      mockMvc.perform(put("/api/courses/5")
              .contentType("application/json")
              .content("""
                  {
                    "name": "Update course",
                    "isArchived": true
                  }"""))
          .andExpect(status().isNotFound());
    }
  }

  @Nested
  @DisplayName("DELETE /courses/{course-id}:")
  public class DeleteCourse {

    @Test
    @Sql(scripts = "/sql/data.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void return_deleted_course() throws Exception {
      mockMvc.perform(delete("/api/courses/1"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.id", is(1)))
          .andExpect(jsonPath("$.name", is("Course 1")))
          .andExpect((jsonPath("$.isArchived", is(false))));
    }

    @Test
    @Sql(scripts = "/sql/data.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void return_404_for_not_existed_course() throws Exception {
      mockMvc.perform(delete("/api/courses/5"))
          .andExpect(status().isNotFound());
    }
  }
}
