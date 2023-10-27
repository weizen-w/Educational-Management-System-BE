package de.ait.ems.services;

import static de.ait.ems.dto.GroupDto.from;

import de.ait.ems.dto.GroupDto;
import de.ait.ems.dto.NewGroupDto;
import de.ait.ems.dto.UpdateGroupDto;
import de.ait.ems.exceptions.RestException;
import de.ait.ems.models.Course;
import de.ait.ems.models.Group;
import de.ait.ems.repositories.CoursesRepository;
import de.ait.ems.repositories.GroupsRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * 14/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@RequiredArgsConstructor
@Service
public class GroupsService {

  private final GroupsRepository groupsRepository;
  private final CoursesRepository coursesRepository;

  public GroupDto addGroup(NewGroupDto newGroup) {
    Group group = Group.builder()
        .name(newGroup.getName())
        .course(getCourseOrThrow(newGroup.getCourseId()))
        .archived(false)
        .build();
    groupsRepository.save(group);
    return from(group);
  }

  public List<GroupDto> getGroups() {
    List<Group> groups = groupsRepository.findAll();
    return from(groups);
  }

  public GroupDto getGroup(Long groupId) {
    Group group = getGroupOrThrow(groupId);
    return from(group);
  }

  public GroupDto updateGroup(Long groupId, UpdateGroupDto updateGroup) {
    Group groupForUpdate = getGroupOrThrow(groupId);
    if (updateGroup.getName() != null) {
      groupForUpdate.setName(updateGroup.getName());
    }
    if (updateGroup.getCourseId() != null) {
      groupForUpdate.setCourse(getCourseOrThrow(updateGroup.getCourseId()));
    }
    if (updateGroup.getArchived() != null) {
      groupForUpdate.setArchived(updateGroup.getArchived());
    }
    groupsRepository.save(groupForUpdate);
    return from(groupForUpdate);
  }

  public GroupDto deleteGroup(Long groupId) {
    Group groupForDelete = getGroupOrThrow(groupId);
    groupsRepository.delete(groupForDelete);
    return from(groupForDelete);
  }

  private Group getGroupOrThrow(Long groupId) {
    return groupsRepository.findById(groupId).orElseThrow(
        () -> new RestException(HttpStatus.NOT_FOUND,
            "Group with id <" + groupId + "> not found"));
  }

  private Course getCourseOrThrow(Long courseId) {
    return coursesRepository.findById(courseId).orElseThrow(
        () -> new RestException(HttpStatus.NOT_FOUND,
            "Course with id <" + courseId + "> not found"));
  }
}
