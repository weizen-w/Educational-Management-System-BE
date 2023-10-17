package de.ait.ems.controllers;

import de.ait.ems.controllers.api.GroupsApi;
import de.ait.ems.dto.GroupDto;
import de.ait.ems.dto.NewGroupDto;
import de.ait.ems.dto.UpdateGroupDto;
import de.ait.ems.services.GroupsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * 14/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@RestController
@RequiredArgsConstructor
public class GroupsController implements GroupsApi {

  private final GroupsService groupsService;

  @Override
  public GroupDto addGroup(NewGroupDto newGroup) {
    return groupsService.addGroup(newGroup);
  }

  @Override
  public List<GroupDto> getGroups() {
    return groupsService.getGroups();
  }

  @Override
  public GroupDto getGroup(Long groupId) {
    return groupsService.getGroup(groupId);
  }

  @Override
  public GroupDto updateGroup(Long groupId, UpdateGroupDto updateGroup) {
    return groupsService.updateGroup(groupId, updateGroup);
  }

  @Override
  public GroupDto deleteGroup(Long groupId) {
    return groupsService.deleteGroup(groupId);
  }
}
