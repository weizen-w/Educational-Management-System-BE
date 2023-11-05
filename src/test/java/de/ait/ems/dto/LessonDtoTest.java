package de.ait.ems.dto;

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

@SpringBootTest(classes = TestSecurityConfig.class)
@AutoConfigureMockMvc
@DisplayName("Endpoint /lessons is works:")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class LessonDtoTest {

    @Autowired
    private MockMvc mockMvc;

    @Nested
    @DisplayName("GET /lessons:")
    public class GetLessons {

        @Test
        @WithUserDetails("admin@gmail.com")
        public void return_empty_list_of_lessons_for_empty_database() throws Exception {
            mockMvc.perform(get("/api/lessons"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()", is(0)));
        }

        @Test
        @WithUserDetails("admin@gmail.com")
        public void return_list_of_lessons_for_not_empty_database() throws Exception {
            mockMvc.perform(get("/api/lessons"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()", is(4)))
                    .andExpect(jsonPath("$.[0].id", is(1)))
                    .andExpect(jsonPath("$.[1].id", is(2)))
                    .andExpect(jsonPath("$.[2].id", is(3)))
                    .andExpect(jsonPath("$.[3].id", is(4)))
                    .andExpect(jsonPath("$.[0].lessonTitle", is("Lesson 1")))
                    .andExpect(jsonPath("$.[1].lessonTitle", is("Lesson 2")))
                    .andExpect(jsonPath("$.[2].lessonTitle", is("Lesson 3")))
                    .andExpect(jsonPath("$.[3].lessonTitle", is("Lesson 4")));
        }
    }

    @Nested
    @DisplayName("POST /lessons:")
    public class PostLesson {

        @Test
        @WithUserDetails("admin@gmail.com")
        @Sql(scripts = "/sql/data.sql")
        public void return_created_lesson() throws Exception {
            mockMvc.perform(post("/api/lessons")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                  {
                    "lessonTitle": "New Lesson"
                  }"""))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id", is(5)));
        }

        @Test
        @WithUserDetails("admin@gmail.com")
        public void return_400_for_not_valid_lesson() throws Exception {
            mockMvc.perform(post("/api/lessons")
                            .contentType("application/json")
                            .content("""
                  {
                    "lessonTitle": " "
                  }"""))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.errors.size()", is(1)));
        }
    }

    @Nested
    @DisplayName("GET /lessons/{lesson-id}:")
    public class GetLesson {

        @Test
        @WithUserDetails("admin@gmail.com")
        @Sql(scripts = "/sql/data.sql")
        public void return_existed_lesson() throws Exception {
            mockMvc.perform(get("/api/lessons/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(1)))
                    .andExpect(jsonPath("$.lessonTitle", is("Lesson 1")))
                    .andExpect((jsonPath("$.archived", is(false))));
        }

        @Test
        @WithUserDetails("admin@gmail.com")
        @Sql(scripts = "/sql/data.sql")
        public void return_404_for_not_existed_lesson() throws Exception {
            mockMvc.perform(get("/api/lessons/5"))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("PUT /lessons/{lesson-id}:")
    public class PutLesson {

        @Test
        @WithUserDetails("admin@gmail.com")
        @Sql(scripts = "/sql/data.sql")
        public void return_updated_lesson() throws Exception {
            mockMvc.perform(put("/api/lessons/1")
                            .contentType("application/json")
                            .content("""
                  {
                    "lessonTitle": "Update lesson",
                    "archived": true
                  }"""))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(1)))
                    .andExpect(jsonPath("$.lessonTitle", is("Update lesson")))
                    .andExpect((jsonPath("$.archived", is(true))));
        }

        @Test
        @WithUserDetails("admin@gmail.com")
        public void return_404_for_not_existed_lesson() throws Exception {
            mockMvc.perform(put("/api/lessons/5")
                            .contentType("application/json")
                            .content("""
                  {
                    "lessonTitle": "Update lesson",
                    "archived": true
                  }"""))
                    .andExpect(status().isNotFound());
        }

        @Test
        @WithUserDetails("admin@gmail.com")
        @Sql(scripts = "/sql/data.sql")
        public void return_400_for_not_valid_update_lesson() throws Exception {
            mockMvc.perform(put("/api/lessons/52")
                            .contentType("application.json")
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