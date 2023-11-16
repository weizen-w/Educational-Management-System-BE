package de.ait.ems.controllers.api;

import de.ait.ems.dto.CourseDto;
import de.ait.ems.dto.NewCourseDto;
import de.ait.ems.dto.StandardResponseDto;
import de.ait.ems.dto.UpdateCourseDto;
import de.ait.ems.validations.dto.ValidationErrorsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 17/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@RequestMapping("/api/courses")
@Tags(value = {
    @Tag(name = "Courses")
})
public interface CoursesApi {

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
  @ResponseStatus(code = HttpStatus.CREATED)
  @PreAuthorize("hasAuthority('ADMIN')")
  CourseDto addCourse(
      @Parameter(description = "Body with new course", required = true) @RequestBody @Valid NewCourseDto newCourse);

  @Operation(summary = "Getting a list of courses", description = "Available to all users. Available to administrator")
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER') or hasAuthority('STUDENT')")
  List<CourseDto> getCourses();

  @Operation(summary = "Getting a course", description = "Available to all users")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "Request processed successfully",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = CourseDto.class))
      ),
      @ApiResponse(responseCode = "404",
          description = "Course not found",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = StandardResponseDto.class)))
  })
  @GetMapping("/{course-id}")
  @ResponseStatus(code = HttpStatus.OK)
  @PreAuthorize("hasAuthority('ADMIN')")
  CourseDto getCourse(
      @Parameter(description = "Course ID", example = "1", required = true) @Min(1) @PathVariable("course-id") Long courseId);

  @Operation(summary = "Course update", description = "Available to administrator")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "Update processed successfully",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = CourseDto.class))
      ),
      @ApiResponse(responseCode = "400",
          description = "Validation error",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = ValidationErrorsDto.class))
      ),
      @ApiResponse(responseCode = "404",
          description = "Course not found",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = StandardResponseDto.class)))
  })
  @PutMapping("/{course-id}")
  @ResponseStatus(code = HttpStatus.OK)
  @PreAuthorize("hasAuthority('ADMIN')")
  CourseDto updateCourse(
      @Parameter(description = "Course ID", example = "1", required = true) @Min(1) @PathVariable("course-id") Long courseId,
      @Parameter(description = "Body with new course", required = true) @Valid @RequestBody UpdateCourseDto updateCourse);
}
