package de.ait.ems.controllers.api;

import de.ait.ems.dto.AttendanceDto;
import de.ait.ems.dto.LessonDto;
import de.ait.ems.dto.NewSubmissionDto;
import de.ait.ems.dto.StandardResponseDto;
import de.ait.ems.dto.SubmissionDto;
import de.ait.ems.dto.UpdateLessonDto;
import de.ait.ems.security.details.AuthenticatedUser;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Oleksandr Zhurba on 01.11.2023.
 * @project Educational-Management-System-BE
 **/
@RequestMapping("/api/lesson")
@Tags(value = {
    @Tag(name = "Lessons", description = "This controller realized management of lessons")
})
@Validated
public interface LessonsApi {

  @Operation(summary = "Lesson update", description = "Update lesson info. Available to administrator")
  @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "Update processed successfully",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = LessonDto.class))
      ),
      @ApiResponse(responseCode = "400",
          description = "Validation error",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = ValidationErrorsDto.class))
      ),
      @ApiResponse(responseCode = "404",
          description = "Group not found",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = StandardResponseDto.class)))
  })
  @PutMapping("/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  LessonDto updateLesson(@Parameter(description = "Lesson ID", example = "1", required = true)
  @PathVariable("id") @Min(1) Long lessonId, @RequestBody @Valid UpdateLessonDto updateLesson);


  @Operation(summary = "Getting a list of attendance lesson", description = "Return list of attendance for selected lesson. Available to ADMIN or TEACHER")
  @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
  @GetMapping("/{id}/attendance")
  @ResponseStatus(code = HttpStatus.OK)
  List<AttendanceDto> getAttendanceByLesson(
      @Parameter(description = "Lesson ID", example = "1", required = true)
      @PathVariable("id") @Min(1) Long lessonId);


  @Operation(summary = "Get lessons list by teacher", description = "Return lessons list by teacher. Available to ADMIN or TEACHER")
  @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "Lessons returned successfully. ",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = LessonDto.class))),
      @ApiResponse(responseCode = "400",
          description = "Validation error",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = ValidationErrorsDto.class)))
  })
  @GetMapping("/byTeacher")
  @ResponseStatus(code = HttpStatus.OK)
  List<LessonDto> getLessonsByTeacher(
      @Parameter(hidden = true) @AuthenticationPrincipal AuthenticatedUser user);

  @Operation(summary = "Get lesson by id", description = "Return lesson by id. Allowed to Admin and teacher")
  @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('STUDENT') or hasAuthority('TEACHER')")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "Lessons returned successfully. ",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = LessonDto.class))),
      @ApiResponse(responseCode = "400",
          description = "Validation error",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = ValidationErrorsDto.class)))
  })
  @GetMapping("/{lesson-id}")
  @ResponseStatus(code = HttpStatus.OK)
  LessonDto getLessonById(
      @Parameter(description = "Lesson ID", example = "1", required = true)
      @PathVariable("lesson-id") @Min(1) Long lessonId);

  @Operation(summary = "Get submissions by lesson", description = "Return submissions by lesson. Allowed to Admin and teacher")
  @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER') or hasAuthority('STUDENT')")
  @GetMapping("/{lesson-id}/submissions")
  @ResponseStatus(code = HttpStatus.OK)
  List<SubmissionDto> getSubmissionsByLesson(
      @Parameter(description = "Lesson ID", example = "1", required = true)
      @PathVariable("lesson-id") @Min(1) Long lessonId);

  @Operation(summary = "Post submission by lesson", description = "Add new submission by lesson. Allowed to auth user")
  @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER') or hasAuthority('STUDENT')")
  @PostMapping("/{lesson-id}/submissions")
  @ResponseStatus(code = HttpStatus.OK)
  SubmissionDto addSubmissionsByLesson(
      @RequestBody @Valid @Parameter(description = "Body with new submission", required = true)
      NewSubmissionDto newSubmissionDto,
      @Parameter(description = "Lesson ID", example = "1", required = true)
      @PathVariable("lesson-id") @Min(1) Long lessonId,
      @Parameter(hidden = true) @AuthenticationPrincipal AuthenticatedUser user);
}
