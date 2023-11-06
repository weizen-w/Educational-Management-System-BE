package de.ait.ems.models;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

/**
 * @author Oleksandr Zhurba on 03.11.2023.
 * @project Educational-Management-System-BE
 **/
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "attendance")
public class Attendance {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "attendance_id", nullable = false)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "student_id", nullable = false)
  private User student;

  @ManyToOne
  @JoinColumn(name = "lesson_id", nullable = false)
  private Lesson lesson;

  @Column(name = "attendance_date", nullable = false)
  private LocalDate attendanceDate;

  @Column(name = "status", nullable = false)
  private String status;

  @Column(name = "archived", nullable = false)
  @ColumnDefault("false")
  private Boolean archived;
}
