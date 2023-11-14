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
 * DTO for {@link de.ait.ems.models.Submission}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "New submission", description = "New submission")
public class NewSubmissionDto implements Serializable {

  @NotNull(message = "Must not be null")
  @NotEmpty(message = "Must not be empty")
  @NotBlank(message = "Must not be blank")
  String description;
}