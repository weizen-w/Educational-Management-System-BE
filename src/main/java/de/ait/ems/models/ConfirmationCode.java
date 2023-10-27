package de.ait.ems.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 26/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "confirmation_code")
public class ConfirmationCode {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "code_id")
  private Long id;

  @Column(name = "code", nullable = false, unique = true)
  private String code;

  @ManyToOne
  @JoinColumn(name = "account_id", nullable = false)
  private User user;

  @Column(name = "expired_date_time", nullable = false)
  private LocalDateTime expiredDateTime;
}
