package de.ait.ems.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Oleksandr Zhurba on 15.11.2023.
 * @project Educational-Management-System-BE
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "Material", description = "Material contents theory (and in a future homework)")
public class MaterialDto {

  @Schema(description = "order", example = "09")
  private String order;
  @Schema(description = "MD content", example = "long long string")
  private String mdContent;
}
