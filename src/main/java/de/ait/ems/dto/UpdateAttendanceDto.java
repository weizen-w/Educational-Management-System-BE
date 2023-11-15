package de.ait.ems.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * DTO for {@link de.ait.ems.models.Attendance}
 */
@Data
@Schema(name = "Update attendance", description = "Update lesson attendance")
public class UpdateAttendanceDto implements Serializable {

  @Schema(description = "Status", example = "CONFIRMED")
  @Pattern(message = "Must not be blank or contain only spaces", regexp = "^$|^(?!\\\\s+$).+")
  @Length(message = "Size must be in the range from 1 to 20", min = 1, max = 20)
  String status;
  @Schema(description = "archived", example = "false")
  @NotNull(message = "Must not be null")
  Boolean archived;
}