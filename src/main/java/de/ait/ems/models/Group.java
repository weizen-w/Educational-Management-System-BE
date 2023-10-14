package de.ait.ems.models;

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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

/**
 * 14/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "student_groups")
@EqualsAndHashCode(exclude = "course")
public class Group {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "group_id")
  private Long id;
  @Column(name = "group_name", length = 150, nullable = false)
  private String name;
  @ManyToOne
  @JoinColumn(name = "course_id", nullable = false)
  @ToString.Exclude
  private Course course;
  @Column(name = "is_archived", nullable = false)
  @ColumnDefault("false")
  private Boolean isArchived;
}
