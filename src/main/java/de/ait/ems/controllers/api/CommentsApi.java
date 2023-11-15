package de.ait.ems.controllers.api;

import de.ait.ems.dto.CommentDto;
import de.ait.ems.dto.StandardResponseDto;
import de.ait.ems.dto.UpdateCommentDto;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Oleksandr Zhurba on 08.11.2023.
 * @project Educational-Management-System-BE
 **/
@RequestMapping("/api/comments")
@Tags(value = {
    @Tag(name = "Comments")
})
public interface CommentsApi {

  @Operation(summary = "Update comment by ID", description = "Update comment. Available to administrator")
  @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER') or hasAuthority('STUDENT')")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "Update processed successfully",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = CommentDto.class))
      ),
      @ApiResponse(responseCode = "400",
          description = "Validation error",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = ValidationErrorsDto.class))
      ),
      @ApiResponse(responseCode = "404",
          description = "Comment not found",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = StandardResponseDto.class)))
  })
  @PutMapping("/{comment-id}")
  CommentDto updateComment(
      @Parameter(description = "Comment ID", example = "1", required = true) @PathVariable("comment-id") @Min(1) Long commentId,
      @RequestBody @Valid UpdateCommentDto updateCommentDto);

  @Operation(summary = "Delete comment by ID", description = "Delete comment. Available to administrator")
  @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER') or hasAuthority('STUDENT')")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "Delete processed successfully",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = CommentDto.class))
      ),
      @ApiResponse(responseCode = "400",
          description = "Validation error",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = ValidationErrorsDto.class))
      ),
      @ApiResponse(responseCode = "404",
          description = "Comment not found",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = StandardResponseDto.class)))
  })
  @DeleteMapping("/{comment-id}")
  CommentDto deleteComment(@Parameter(description = "Comment ID", example = "1", required = true)
  @PathVariable("comment-id") @Min(1) Long commentId);
}
