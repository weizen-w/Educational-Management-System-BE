package de.ait.ems.models;

import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;
import org.hibernate.proxy.HibernateProxy;

/**
 * @project EducationalManagementSystem
 * @AUTHOR Oleksandr Zhurba on 11.10.2023.
 **/
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "account")
public class User {

  public enum Role {
    ADMIN, STUDENT, TEACHER
  }

  public enum State {
    NOT_CONFIRMED, CONFIRMED, DELETED, BANNED
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "account_id")
  private Long id;
  @Column(name = "hash_password", length = 100, nullable = false)
  private String hashPassword;
  @Column(name = "first_name", length = 50, nullable = false)
  private String firstName;
  @Column(name = "last_name", length = 50, nullable = false)
  private String lastName;
  @Column(name = "email", unique = true, nullable = false)
  private String email;
  @Column(name = "role", nullable = false)
  @Enumerated(value = EnumType.STRING)
  private Role role;
  @Column(name = "account_state", nullable = false)
  @Enumerated(value = EnumType.STRING)
  private State state;

  @OneToMany(mappedBy = "user")
  @Exclude
  private Set<ConfirmationCode> codes;

  private String photoLink;
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
    User user = (User) object;
    return getId() != null && Objects.equals(getId(), user.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer()
        .getPersistentClass().hashCode() : getClass().hashCode();
  }
}
