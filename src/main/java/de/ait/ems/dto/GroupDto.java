package de.ait.ems.dto;

import de.ait.ems.models.Group;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 14/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "Group(student)", description = "Group description")
public class GroupDto {

  @Schema(description = "Group ID", example = "1")
  private Long id;
  @Schema(description = "Group name", example = "Cohort-26")
  private String name;
  @Schema(description = "Group course ID", example = "1")
  private Long courseId;
  @Schema(description = "Group is archived", example = "false")
  private Boolean isArchived;

  public static GroupDto from(Group group) {
    return GroupDto.builder()
        .id(group.getId())
        .name(group.getName())
        .courseId(group.getCourse().getId())
        .isArchived(group.getIsArchived())
        .build();
  }

  public static List<GroupDto> from(List<Group> groups) {
    return groups
        .stream()
        .map(GroupDto::from)
        .collect(Collectors.toList());
  }
}
