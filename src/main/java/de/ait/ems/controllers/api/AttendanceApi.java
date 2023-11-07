package de.ait.ems.controllers.api;

import de.ait.ems.dto.AttendanceDto;
import de.ait.ems.dto.StandardResponseDto;
import de.ait.ems.dto.UpdateAttendanceDto;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Oleksandr Zhurba on 03.11.2023.
 * @project Educational-Management-System-BE
 **/
@RequestMapping("/api/attendances")
@Tags(value = {
    @Tag(name = "Attendance")
})
public interface AttendanceApi {
  @Operation(summary = "Attendance update", description = "Available to administrator")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "Update processed successfully",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = AttendanceDto.class))
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
  @PutMapping("/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
  AttendanceDto updateAttendance(
      @Parameter(description = "Attendance ID", example = "1", required = true) @Min(1) @PathVariable("id") Long attendanceId,
      @Parameter(description = "Body with changes to attendance", required = true) @Valid @RequestBody UpdateAttendanceDto updateAttendance);

  @Operation(summary = "Getting a list of attendances by authenticated user", description = "Return list of all attendances by auth user. Available to authenticated user")
  @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('STUDENT') or hasAuthority('TEACHER')")
  @GetMapping("/byAuthUser")
  @ResponseStatus(code = HttpStatus.OK)
  List<AttendanceDto> getAttendanceByAuthUser(
      @Parameter(hidden = true) @AuthenticationPrincipal AuthenticatedUser user);

}
