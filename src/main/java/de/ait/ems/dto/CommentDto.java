package de.ait.ems.dto;

import de.ait.ems.models.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for {@link de.ait.ems.models.Comment}
 * @author Oleksandr Zhurba on 07.11.2023.
 * @project Educational-Management-System-BE
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "Comment", description = "Comment for submission")
public class CommentDto implements Serializable {
  @Schema(description = "Comment ID", example = "1")
  Long comment_id;
  @Schema(description = "Submission ID", example = "1")
  Long submission_id;
  @Schema(description = "Sender (author) ID", example = "1")
  Long author_id;
  @Schema(description = "Description", example = "Some comment under submission")
  String messageText;
  @Schema(description = "DateTime", example = "2023-11-27 10:15:30")
  LocalDateTime messageDate;
  @Schema(description = "Archived", example = "false")
  Boolean archived;

  public static CommentDto from(Comment comment) {
    return CommentDto.builder()
        .comment_id(comment.getId())
        .submission_id(comment.getSubmission().getId())
        .author_id(comment.getAuthor().getId())
        .messageText(comment.getMessageText())
        .messageDate(comment.getMessageDate())
        .archived(comment.getArchived())
        .build();
  }

  public static List<CommentDto> from(List<Comment> comments) {
    return comments.stream()
        .map(CommentDto::from)
        .collect(Collectors.toList());
  }
}
