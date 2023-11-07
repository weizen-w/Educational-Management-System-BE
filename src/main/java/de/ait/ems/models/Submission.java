package de.ait.ems.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
 * @author Oleksandr Zhurba on 07.11.2023.
 * @project Educational-Management-System-BE
 **/
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "submission")

public class Submission {
  public enum Status {
    NEW, GIVEN, VIEWED
  }
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "submission_id")
  private Long id;
  @Column(name = "description", nullable = false)
  private String description;
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "lesson_id", nullable = false)
  private Lesson lesson;
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "student_id", nullable = false)
  @ToString.Exclude
  private User student;
  @Column(name = "submission_state", nullable = false)
  @Enumerated(value = EnumType.STRING)
  private Status state;
  @Column(name = "archived", nullable = false)
  @ColumnDefault("false")
  private Boolean archived;
}
