package de.ait.ems.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
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
import org.hibernate.proxy.HibernateProxy;


/**
 * 14/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "lesson")
public class Lesson {
  public enum LessonType {
    CONSULTATION, LESSON, PRACTICE, PROJECT
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "lesson_id")
  private Long id;
  @ManyToOne
  @JoinColumn(name = "group_id", nullable = false)
  @ToString.Exclude
  private Group group;
  @Column(name = "lesson_title", length = 50, nullable = false)
  private String lessonTitle;
  @Column(name = "lesson_description")
  private String lessonDescription;
  @Column(name = "lesson_type")
  @Enumerated(value = EnumType.STRING)
  private LessonType lessonType;
  @ManyToOne
  @JoinColumn(name = "teacher_id", nullable = false)
  @ToString.Exclude
  private User teacher;
  @Column(name = "lesson_date", nullable = false)

  private LocalDate lessonDate;
  @Column(name = "start_time", nullable = false)

  private LocalTime startTime;
  @Column(name = "end_time", nullable = false)

  private LocalTime endTime;
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "module_id", nullable = false)
  @ToString.Exclude
  private Module module;
  @Column(name = "link_lms")
  private String linkLms;
  @Column(name = "link_zoom")
  private String linkZoom;
  @Column(name = "archived", nullable = false)
  @ColumnDefault("false")
  private Boolean archived;

  @Override
  public final boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null) {
      return false;
    }
    Class<?> oEffectiveClass = object instanceof HibernateProxy
        ? ((HibernateProxy) object).getHibernateLazyInitializer()
        .getPersistentClass() : object.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy
        ? ((HibernateProxy) this).getHibernateLazyInitializer()
        .getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) {
      return false;
    }
    Lesson lesson = (Lesson) object;
    return getId() != null && Objects.equals(getId(), lesson.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer()
        .getPersistentClass().hashCode() : getClass().hashCode();
  }
}
