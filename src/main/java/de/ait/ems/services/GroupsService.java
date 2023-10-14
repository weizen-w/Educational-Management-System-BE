package de.ait.ems.services;

import de.ait.ems.dto.GroupDto;
import de.ait.ems.dto.NewGroupDto;
import de.ait.ems.dto.UpdateGroupDto;
import java.util.List;

/**
 * 14/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
public interface GroupsService {

  GroupDto addGroup(NewGroupDto newGroup);

  List<GroupDto> getGroups();

  GroupDto getGroup(Long groupId);

  GroupDto updateGroup(Long groupId, UpdateGroupDto updateGroup);

  GroupDto deleteGroup(Long groupId);
}
