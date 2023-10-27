package de.ait.ems.dto;

import de.ait.ems.models.Group;
import de.ait.ems.models.User;
import de.ait.ems.models.UserGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 27/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "User in group", description = "User to group association")
public class UserGroupDto {

  @Schema(description = "User to group ID", example = "1")
  private Long id;
  @Schema(description = "User ID", example = "1")
  private Long userId;
  @Schema(description = "Group ID", example = "1")
  private Long groupId;
  @Schema(description = "Is main group for user", example = "true")
  private Boolean mainGroup;

  public static UserGroupDto from(UserGroup userGroup) {
    return UserGroupDto.builder()
        .id(userGroup.getId())
        .userId(userGroup.getUser().getId())
        .groupId(userGroup.getGroup().getId())
        .mainGroup(userGroup.getMainGroup())
        .build();
  }

  public static List<UserGroupDto> from(List<UserGroup> userGroups) {
    return userGroups.stream()
        .map(UserGroupDto::from)
        .collect(Collectors.toList());
  }
}
