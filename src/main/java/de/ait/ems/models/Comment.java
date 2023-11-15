package de.ait.ems.models;

import java.time.LocalDateTime;
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
 * @author Oleksandr Zhurba on 08.11.2023.
 * @project Educational-Management-System-BE
 **/
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "comment")
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "comment_id")
  private Long id;
  @ManyToOne
  @JoinColumn(name = "submission_id", nullable = false)
  @ToString.Exclude
  private Submission submission;
  @ManyToOne
  @JoinColumn(name = "sender_id", nullable = false)
  @ToString.Exclude
  private User author;
  @Column(name = "message_text", nullable = false)
  private String messageText;
  @Column(name = "message_date", nullable = false)
  private LocalDateTime messageDate;
  @Column(name = "archived", nullable = false)
  @ColumnDefault("false")
  private Boolean archived;
}
