package de.ait.ems.services;

import de.ait.ems.dto.AttendanceDto;
import de.ait.ems.dto.UpdateAttendanceDto;
import de.ait.ems.exceptions.RestException;
import de.ait.ems.mapper.EntityMapper;
import de.ait.ems.models.Attendance;
import de.ait.ems.models.Attendance.Status;
import de.ait.ems.models.User;
import de.ait.ems.repositories.AttendanceRepository;
import de.ait.ems.security.details.AuthenticatedUser;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author Oleksandr Zhurba on 03.11.2023.
 * @project Educational-Management-System-BE
 **/
@RequiredArgsConstructor
@Service
public class AttendanceService {

  private final EntityMapper entityMapper;
  private final AttendanceRepository attendanceRepository;
  private final UsersService usersService;

  public AttendanceDto updateAttendance(Long attendanceId, UpdateAttendanceDto updateAttendance) {
    Attendance attendanceForUpdate = getAttendanceOrThrow(attendanceId);
    if (updateAttendance.getStatus() != null) {
      attendanceForUpdate.setStatus(Status.valueOf(updateAttendance.getStatus()));
    }
    if (updateAttendance.getArchived() != null) {
      attendanceForUpdate.setArchived(updateAttendance.getArchived());
    }
    attendanceRepository.save(attendanceForUpdate);
    return AttendanceDto.from(attendanceForUpdate);
  }

  private Attendance getAttendanceOrThrow(Long attendanceId) {
    return attendanceRepository.findById(attendanceId).orElseThrow(
        () -> new RestException(HttpStatus.NOT_FOUND,
            "Attendance with id <" + attendanceId + "> not found"));
  }

  public List<AttendanceDto> getAttendancesByAuthUser(AuthenticatedUser user) {
    User userEntity = usersService.getUserOrThrow(user.getId());
    if (userEntity != null) {
      return attendanceRepository.getAttendanceByStudent(userEntity).stream()
          .map(entityMapper::convertToDto)
          .collect(Collectors.toList());
    }
    return null;
  }
}
