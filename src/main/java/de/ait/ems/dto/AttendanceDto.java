package de.ait.ems.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
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

}
