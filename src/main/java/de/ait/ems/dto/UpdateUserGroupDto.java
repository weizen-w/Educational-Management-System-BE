package de.ait.ems.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 27/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Update user-group association ", description = "Data for updating")
public class UpdateUserGroupDto {

  @Schema(description = "Is main group for user", example = "false")
  private Boolean mainGroup;
}
