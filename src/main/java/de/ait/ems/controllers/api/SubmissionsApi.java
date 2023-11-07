package de.ait.ems.controllers.api;

import de.ait.ems.dto.StandardResponseDto;
import de.ait.ems.dto.SubmissionDto;
import de.ait.ems.dto.UpdateSubmissionDto;
import de.ait.ems.validations.dto.ValidationErrorsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Oleksandr Zhurba on 07.11.2023.
 * @project Educational-Management-System-BE
 **/
@RequestMapping("/api/submissions")
@Tags(value = {@Tag(name = "Submissions")})
public interface SubmissionsApi {

  @Operation(summary = "Update submission by ID", description = "Update submission. Available to administrator")
  @PreAuthorize("hasAuthority('ADMIN')")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "Update processed successfully",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = SubmissionDto.class))
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
  @PutMapping("/{submission-id}")
  SubmissionDto updateSubmission(
      @Parameter(description = "Submission ID", example = "1", required = true) @PathVariable("submission-id") @Min(1) Long submissionId,
      @RequestBody @Valid UpdateSubmissionDto updateSubmissionDto);

  @Operation(summary = "Getting a submission", description = "Return one submission by requested id. Available to administrator")
  @PreAuthorize("hasAuthority('ADMIN')")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "Request processed successfully",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = SubmissionDto.class))
      ),
      @ApiResponse(responseCode = "404",
          description = "Group not found",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = StandardResponseDto.class)))
  })
  @GetMapping("/{submission-id}")
  @ResponseStatus(code = HttpStatus.OK)
  SubmissionDto getSubmission(@Parameter(description = "Submission ID", example = "1")
  @PathVariable("submission-id") @Min(1) Long submissionId);
}
