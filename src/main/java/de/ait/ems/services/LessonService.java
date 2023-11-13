package de.ait.ems.services;

import de.ait.ems.dto.AttendanceDto;
import de.ait.ems.dto.LessonDto;
import de.ait.ems.dto.NewLessonDto;
import de.ait.ems.dto.UpdateLessonDto;
import de.ait.ems.exceptions.RestException;
import de.ait.ems.mapper.EntityMapper;
import de.ait.ems.models.Attendance;
import de.ait.ems.models.Group;
import de.ait.ems.models.Lesson;
import de.ait.ems.models.Lesson.LessonType;
import de.ait.ems.models.User;
import de.ait.ems.models.UserGroup;
import de.ait.ems.repositories.AttendanceRepository;
import de.ait.ems.repositories.LessonRepository;
import de.ait.ems.repositories.UserGroupsRepository;
import de.ait.ems.security.details.AuthenticatedUser;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author Oleksandr Zhurba on 01.11.2023.
 * @project Educational-Management-System-BE
 **/
@RequiredArgsConstructor
@Service
public class LessonService {

  private final UsersService usersService;
  private final LessonRepository lessonRepository;
  private final UserGroupsRepository userGroupsRepository;
  private final ModulesService modulesService;
  private final GroupsService groupsService;
  private final AttendanceRepository attendanceRepository;
  private final EntityMapper entityMapper;
  private final AttendanceService attendanceService;

  public List<LessonDto> getLessonByGroup(Long groupId) {
    List<LessonDto> result = new ArrayList<>();
    List<Lesson> lessonList = lessonRepository.findByGroupId(groupId);
    lessonList.forEach(lesson -> result.add(LessonDto.from(lesson)));
    return result;
  }

  public LessonDto addLesson(NewLessonDto newLesson, Long groupId) {
    Lesson lesson = Lesson.builder()
        .lessonTitle(newLesson.getLessonTitle())
        .lessonDescription(newLesson.getLessonDescription())
        .lessonType(LessonType.valueOf(newLesson.getLessonType()))
        .teacher(usersService.getUserOrThrow(newLesson.getTeacherId()))
        .lessonDate(newLesson.getLessonDate())
        .startTime(newLesson.getStartTime())
        .endTime(newLesson.getEndTime())
        .module(modulesService.getModuleOrThrow(newLesson.getModuleId()))
        .linkLms(newLesson.getLinkLms())
        .linkZoom(newLesson.getLinkZoom())
        .group(groupsService.getGroupOrThrow(groupId))
        .archived(false)
        .build();
    lessonRepository.save(lesson);
    attendanceService.addAttendanceByNewLesson(lesson, groupId);
    return LessonDto.from(lesson);
  }

  public LessonDto updateLesson(UpdateLessonDto updateLesson, Long lessonId) {
    Lesson lessonForUpdate = getLessonOrThrow(lessonId);
    if (updateLesson.getGroupId() != null) {
      lessonForUpdate.setGroup(groupsService.getGroupOrThrow(updateLesson.getGroupId()));
    }
    if (updateLesson.getLessonTitle() != null) {
      lessonForUpdate.setLessonTitle(updateLesson.getLessonTitle());
    }
    if (updateLesson.getLessonDescription() != null) {
      lessonForUpdate.setLessonDescription(updateLesson.getLessonDescription());
    }
    if (updateLesson.getLessonType() != null) {
      lessonForUpdate.setLessonType(LessonType.valueOf(updateLesson.getLessonType()));
    }
    if (updateLesson.getTeacherId() != null) {
      lessonForUpdate.setTeacher(usersService.getUserOrThrow(updateLesson.getTeacherId()));
    }
    if (updateLesson.getLessonDate() != null) {
      lessonForUpdate.setLessonDate(updateLesson.getLessonDate());
    }
    if (updateLesson.getStartTime() != null) {
      lessonForUpdate.setStartTime(updateLesson.getStartTime());
    }
    if (updateLesson.getEndTime() != null) {
      lessonForUpdate.setEndTime(updateLesson.getEndTime());
    }
    if (updateLesson.getModuleId() != null) {
      lessonForUpdate.setModule(modulesService.getModuleOrThrow(updateLesson.getModuleId()));
    }
    if (updateLesson.getLinkLms() != null) {
      lessonForUpdate.setLinkLms(updateLesson.getLinkLms());
    }
    if (updateLesson.getLinkZoom() != null) {
      lessonForUpdate.setLinkZoom(updateLesson.getLinkZoom());
    }
    if (updateLesson.getArchived() != null) {
      lessonForUpdate.setArchived(updateLesson.getArchived());
    }
    lessonRepository.save(lessonForUpdate);
    return LessonDto.from(lessonForUpdate);
  }

  public Lesson getLessonOrThrow(Long lessonId) {
    return lessonRepository.findById(lessonId).orElseThrow(
        () -> new RestException(HttpStatus.NOT_FOUND,
            "Lesson with id <" + lessonId + "> not found"));
  }

  public List<AttendanceDto> getAttendanceByLesson(Long lessonId) {
    Lesson lesson = getLessonOrThrow(lessonId);
    if (lesson != null) {
      List<Attendance> attendanceList = attendanceRepository.getAttendanceByLesson(lesson);
      return AttendanceDto.from(attendanceList);
    }
    return null;
  }

  public List<LessonDto> getLessonByAuthUser(AuthenticatedUser authUser) {
    User user = usersService.getUserOrThrow(authUser.getId());
    if (user != null) {
      List<UserGroup> groupsByUser = userGroupsRepository.findByUserId(user.getId());
      List<Group> groups = groupsByUser.stream().map(UserGroup::getGroup).toList();
      List<Lesson> lessonList = new ArrayList<>();
      for (Group group : groups) {
        lessonList.addAll(lessonRepository.findByGroupId(group.getId()));
      }
      return lessonList
          .stream()
          .map(entityMapper::convertToDto)
          .collect(Collectors.toList());
    }
    return null;
  }

  public LessonDto getLessonById(Long lessonId) {
    return entityMapper.convertToDto(getLessonOrThrow(lessonId));
  }
}
