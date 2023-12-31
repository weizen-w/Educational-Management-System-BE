package de.ait.ems.mapper;

import de.ait.ems.dto.AttendanceDto;
import de.ait.ems.dto.CommentDto;
import de.ait.ems.dto.LessonDto;
import de.ait.ems.dto.SubmissionDto;
import de.ait.ems.dto.UserDto;
import de.ait.ems.models.Attendance;
import de.ait.ems.models.Comment;
import de.ait.ems.models.Lesson;
import de.ait.ems.models.Submission;
import de.ait.ems.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * @author Oleksandr Zhurba on 03.11.2023.
 * @project Educational-Management-System-BE
 **/
@Component
public class EntityMapper {

  private final ModelMapper modelMapper;

  public EntityMapper() {
    this.modelMapper = new ModelMapper();
  }

  public AttendanceDto convertToDto(Attendance attendance) {
    return modelMapper.map(attendance, AttendanceDto.class);
  }

  public LessonDto convertToDto(Lesson lesson) {
    return modelMapper.map(lesson, LessonDto.class);
  }

  public SubmissionDto convertToDto(Submission submission) {
    return modelMapper.map(submission, SubmissionDto.class);
  }

  public CommentDto convertToDto(Comment comment) {
    return modelMapper.map(comment, CommentDto.class);
  }

  public UserDto convertToDto(User user) {
    return modelMapper.map(user, UserDto.class);
  }

}
