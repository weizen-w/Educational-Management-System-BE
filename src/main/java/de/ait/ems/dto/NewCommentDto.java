package de.ait.ems.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;

/**
 * DTO for {@link de.ait.ems.models.Comment}
 */
@Data
@Schema(name = "New comment", description = "New comment to submission")
public class NewCommentDto implements Serializable {

  @NotNull(message = "Must not be null")
  @Pattern(message = "Must not be blank or contain only spaces", regexp = "^$|^(?!\\\\\\\\s+$).+")
  String messageText;
}
