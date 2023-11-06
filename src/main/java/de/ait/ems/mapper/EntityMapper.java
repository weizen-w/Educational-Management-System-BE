package de.ait.ems.mapper;

import de.ait.ems.dto.AttendanceDto;
import de.ait.ems.models.Attendance;
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
}
