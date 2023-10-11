package de.ait.ems.controllers;

import de.ait.ems.dto.CourseDto;
import de.ait.ems.dto.NewCourseDto;
import de.ait.ems.services.CoursesService;
import de.ait.ems.validations.dto.ValidationErrorsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 08/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
@Tags(value = {
    @Tag(name = "Courses")
})
public class CoursesController {

  private final CoursesService coursesService;

  @Operation(summary = "Create a course", description = "Available to administrator")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201",
          description = "The course was created successfully",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = CourseDto.class))),
      @ApiResponse(responseCode = "400",
          description = "Validation error",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = ValidationErrorsDto.class)))
  })
  @PostMapping
  public ResponseEntity<CourseDto> addCourse(@RequestBody @Valid NewCourseDto newCourse) {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(coursesService.addCourse(newCourse));
  }
}
