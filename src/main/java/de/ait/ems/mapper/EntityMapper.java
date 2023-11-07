package de.ait.ems.mapper;

import de.ait.ems.dto.AttendanceDto;
import de.ait.ems.dto.SubmissionDto;
import de.ait.ems.dto.UpdateSubmissionDto;
import de.ait.ems.dto.UserDto;
import de.ait.ems.models.Attendance;
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
  public SubmissionDto convertToDto(Submission submission) {
    return modelMapper.map(submission, SubmissionDto.class);
  }
  public UserDto convertToDto(User user) {
    return modelMapper.map(user, UserDto.class);
  }

}
