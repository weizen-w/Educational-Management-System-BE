package de.ait.ems.controllers;

import de.ait.ems.controllers.api.LessonsApi;
import de.ait.ems.dto.AttendanceDto;
import de.ait.ems.dto.LessonDto;
import de.ait.ems.dto.NewLessonDto;
import de.ait.ems.dto.UpdateLessonDto;
import de.ait.ems.services.LessonService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Oleksandr Zhurba on 01.11.2023.
 * @project Educational-Management-System-BE
 **/
@RestController
@RequiredArgsConstructor
public class LessonsController implements LessonsApi {

  private final LessonService lessonService;
  @Override
  public List<LessonDto> getLessonsByGroup(Long groupId) {
    return lessonService.getLessonByGroup(groupId);
  }

  @Override
  public LessonDto addLesson(NewLessonDto newLesson, Long groupId) {
    return lessonService.addLesson(newLesson, groupId);
  }

  @Override
  public LessonDto updateLesson(Long lessonId, UpdateLessonDto updateLesson) {
    return lessonService.updateLesson(updateLesson, lessonId);
  }

  @Override
  public List<AttendanceDto> getAttendanceByLesson(Long lessonId) {
    return lessonService.getAttendanceByLesson(lessonId);
  }
}
