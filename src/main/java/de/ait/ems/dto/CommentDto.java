package de.ait.ems.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

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
  @Schema(description = "Sender (author) ID", example = "1")
  Long authorId;
  @Schema(description = "Description", example = "Some comment under submission")
  String messageText;
  @Schema(description = "DateTime", example = "2023-11-27 10:15:30")
  LocalDateTime messageDate;
  @Schema(description = "Archived", example = "false")
  Boolean archived;
}
