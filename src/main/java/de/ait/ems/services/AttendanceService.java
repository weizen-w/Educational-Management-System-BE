package de.ait.ems.services;

import de.ait.ems.dto.AttendanceDto;
import de.ait.ems.dto.UpdateAttendanceDto;
import de.ait.ems.exceptions.RestException;
import de.ait.ems.mapper.EntityMapper;
import de.ait.ems.models.Attendance;
import de.ait.ems.repositories.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author Oleksandr Zhurba on 03.11.2023.
 * @project Educational-Management-System-BE
 **/
@Service
public class AttendanceService {
  @Autowired
  EntityMapper entityMapper;
  private final AttendanceRepository attendanceRepository;

  public AttendanceService(AttendanceRepository attendanceRepository) {
    this.attendanceRepository = attendanceRepository;
  }

  public AttendanceDto updateAttendance(Long attendanceId, UpdateAttendanceDto updateAttendance) {
    Attendance attendanceForUpdate = getAttendanceOrThrow(attendanceId);
    if (attendanceForUpdate.getStatus() != null) {
      attendanceForUpdate.setStatus(attendanceForUpdate.getStatus());
    }
    if (attendanceForUpdate.getArchived() != null) {
      attendanceForUpdate.setArchived(attendanceForUpdate.getArchived());
    }
    attendanceRepository.save(attendanceForUpdate);
    return entityMapper.convertToDto(attendanceForUpdate);
  }

  private Attendance getAttendanceOrThrow(Long attendanceId) {
    return attendanceRepository.findById(attendanceId).orElseThrow(
        () -> new RestException(HttpStatus.NOT_FOUND,
            "Attendance with id <" + attendanceId + "> not found"));
  }
}
