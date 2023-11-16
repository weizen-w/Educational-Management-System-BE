package de.ait.ems.controllers;

import de.ait.ems.controllers.api.GroupsApi;
import de.ait.ems.dto.GroupDto;
import de.ait.ems.dto.LessonDto;
import de.ait.ems.dto.MaterialDto;
import de.ait.ems.dto.NewGroupDto;
import de.ait.ems.dto.NewLessonDto;
import de.ait.ems.dto.UpdateGroupDto;
import de.ait.ems.dto.UserDto;
import de.ait.ems.security.details.AuthenticatedUser;
import de.ait.ems.services.GroupsService;
import de.ait.ems.services.LessonService;
import de.ait.ems.services.MaterialsService;
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
  private final LessonService lessonService;
  private final MaterialsService materialsService;

  @Override
  public List<LessonDto> getLessonsByGroup(Long groupId) {
    return lessonService.getLessonByGroup(groupId);
  }

  @Override
  public LessonDto addLesson(NewLessonDto newLesson, Long groupId) {
    return lessonService.addLesson(newLesson, groupId);
  }

  @Override
  public List<MaterialDto> getMaterialsByGroup(Long groupId) {
    return materialsService.getMaterialsByGroupId(groupId);
  }

  @Override
  public GroupDto addGroup(NewGroupDto newGroup) {
    return groupsService.addGroup(newGroup);
  }

  @Override
  public List<GroupDto> getGroups() {
    return groupsService.getGroups();
  }

  @Override
  public List<GroupDto> getGroupsByAuthUser(AuthenticatedUser user) {
    return groupsService.getGroupsByAuthUser(user);
  }

  @Override
  public List<UserDto> getUsersFromGroup(Long groupId) {
    return groupsService.getUsersFromGroup(groupId);
  }

  @Override
  public List<UserDto> getUsersFromGroupByMainGroup(Long groupId) {
    return groupsService.getUsersFromGroupByMainGroup(groupId);
  }

  @Override
  public GroupDto getMainGroupByAuthUser(AuthenticatedUser user) {
    return groupsService.getMainGroupByAuthUser(user);
  }

  @Override
  public GroupDto getGroup(Long groupId) {
    return groupsService.getGroup(groupId);
  }

  @Override
  public GroupDto updateGroup(Long groupId, UpdateGroupDto updateGroup) {
    return groupsService.updateGroup(groupId, updateGroup);
  }
}
