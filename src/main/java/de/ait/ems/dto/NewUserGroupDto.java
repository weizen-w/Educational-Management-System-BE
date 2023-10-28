package de.ait.ems.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 27/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@Data
@Schema(name = "New user-group association", description = "User-group association")
public class NewUserGroupDto {

  @Schema(description = "User ID", example = "1")
  @NotNull(message = "Must not be null")
  @Min(value = 1, message = "Can't be zero or negative")
  private Long userId;
  @Schema(description = "Group ID", example = "1")
  @NotNull(message = "Must not be null")
  @Min(value = 1, message = "Can't be zero or negative")
  private Long groupId;
  @Schema(description = "Is main group for User", example = "true")
  @NotNull(message = "Must not be null")
  private Boolean mainGroup;
}
