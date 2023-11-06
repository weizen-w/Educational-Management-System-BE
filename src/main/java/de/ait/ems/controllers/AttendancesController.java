package de.ait.ems.controllers;

import de.ait.ems.controllers.api.AttendanceApi;
import de.ait.ems.dto.AttendanceDto;
import de.ait.ems.dto.UpdateAttendanceDto;
import de.ait.ems.services.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Oleksandr Zhurba on 03.11.2023.
 * @project Educational-Management-System-BE
 **/
@RestController
@RequiredArgsConstructor
public class AttendancesController implements AttendanceApi {
  @Autowired
  AttendanceService attendanceService;

  @Override
  public AttendanceDto updateAttendance(Long attendanceId, UpdateAttendanceDto updateAttendance) {
    return attendanceService.updateAttendance(attendanceId,updateAttendance);
  }
}
