package de.ait.ems.dto;

import de.ait.ems.models.Attendance;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;

/**
 * @author Oleksandr Zhurba on 03.11.2023.
 * @project Educational-Management-System-BE
 **/
@Data
@Builder
@Schema(name = "Attendance", description = "Lesson attendance")
public class AttendanceDto {

  private Long attendance_id;
  private Long student_id;
  private Long lesson_id;
  private LocalDate attendanceDate;
  private String status;
  private Boolean archived;

  public static AttendanceDto from(Attendance attendance) {
    return AttendanceDto.builder()
        .attendance_id(attendance.getId())
        .student_id(attendance.getStudent().getId())
        .lesson_id(attendance.getLesson().getId())
        .attendanceDate(attendance.getAttendanceDate())
        .status(attendance.getStatus())
        .archived(attendance.getArchived())
        .build();
  }
  public static List<AttendanceDto> from(List<Attendance> attendances) {
    return attendances
        .stream()
        .map(AttendanceDto::from)
        .collect(Collectors.toList());
  }
}
