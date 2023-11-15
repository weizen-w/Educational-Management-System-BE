package de.ait.ems.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for {@link de.ait.ems.models.Comment}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "New comment", description = "New comment to submission")
public class NewCommentDto implements Serializable {

  @NotNull(message = "Must not be null")
  @NotBlank
  @NotEmpty
  private String messageText;
}
