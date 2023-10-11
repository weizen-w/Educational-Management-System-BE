package de.ait.ems.validations.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 08/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "ValidationError", description = "Description of the validation error")
public class ValidationErrorDto {

  @Schema(description = "Name of the field in which the error occurred", example = "price")
  private String field;
  @Schema(description = "The value entered by the user and which was rejected by the server", example = "1000.0")
  private String rejectedValue;
  @Schema(description = "Message to user", example = "Must be less than or equal to 200")
  private String message;
}
