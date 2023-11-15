package de.ait.ems.dto;

import de.ait.ems.models.Module;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 01/11/2023 EducationalManagementSystemBE
 *
 * @author Wladimir Weizen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "Module", description = "Lesson module")
public class ModuleDto {

  @Schema(description = "Module ID", example = "1")
  private Long id;
  @Schema(description = "Module name", example = "Backend")
  private String name;
  @Schema(description = "Module is archived", example = "false")
  private Boolean archived;

  public static ModuleDto from(Module module) {
    return ModuleDto.builder()
        .id(module.getId())
        .name(module.getName())
        .archived(module.getArchived())
        .build();
  }

  public static List<ModuleDto> from(List<Module> modules) {
    return modules
        .stream()
        .map(ModuleDto::from)
        .collect(Collectors.toList());
  }
}
