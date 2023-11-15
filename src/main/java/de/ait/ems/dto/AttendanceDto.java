package de.ait.ems.dto;

import de.ait.ems.models.Attendance;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Oleksandr Zhurba on 03.11.2023.
 * @project Educational-Management-System-BE
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Attendance", description = "Lesson attendance")
public class AttendanceDto {

  @Schema(description = "Attendance ID", example = "1")
  private Long attendance_id;
  @Schema(description = "Student ID", example = "1")
  private Long student_id;
  @Schema(description = "Lesson ID", example = "1")
  private Long lesson_id;
  @Schema(description = "Attendance", example = "2023-11-27")
  private LocalDate attendanceDate;
  @Schema(description = "Attendance status", example = "CONFIRMED")
  private String status;
  @Schema(description = "Attendance is archived", example = "false")
  private Boolean archived;

  public static AttendanceDto from(Attendance attendance) {
    return AttendanceDto.builder()
        .attendance_id(attendance.getId())
        .student_id(attendance.getStudent().getId())
        .lesson_id(attendance.getLesson().getId())
        .attendanceDate(attendance.getAttendanceDate())
        .status(attendance.getStatus().name())
        .archived(attendance.getArchived())
        .build();
  }

  public static List<AttendanceDto> from(List<Attendance> attendanceList) {
    return attendanceList.stream()
        .map(AttendanceDto::from)
        .collect(Collectors.toList());
  }
}
