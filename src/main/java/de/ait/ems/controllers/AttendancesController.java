package de.ait.ems.controllers;

import de.ait.ems.controllers.api.AttendanceApi;
import de.ait.ems.dto.AttendanceDto;
import de.ait.ems.dto.UpdateAttendanceDto;
import de.ait.ems.security.details.AuthenticatedUser;
import de.ait.ems.services.AttendanceService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Oleksandr Zhurba on 03.11.2023.
 * @project Educational-Management-System-BE
 **/
@RestController
@RequiredArgsConstructor
public class AttendancesController implements AttendanceApi {

  private final AttendanceService attendanceService;

  @Override
  public AttendanceDto updateAttendance(Long attendanceId, UpdateAttendanceDto updateAttendance) {
    return attendanceService.updateAttendance(attendanceId, updateAttendance);
  }

  @Override
  public List<AttendanceDto> getAttendanceByAuthUser(AuthenticatedUser user) {
    return attendanceService.getAttendancesByAuthUser(user);
  }
}
