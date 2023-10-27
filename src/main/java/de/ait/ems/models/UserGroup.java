package de.ait.ems.models;

import java.util.Objects;
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
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

/**
 * 27/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "account_group")
public class UserGroup {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "account_group_id")
  private Long id;
  @ManyToOne
  @JoinColumn(name = "account_id", nullable = false)
  @ToString.Exclude
  private User user;
  @ManyToOne
  @JoinColumn(name = "group_id", nullable = false)
  @ToString.Exclude
  private Group group;
  @JoinColumn(name = "main_group", nullable = false)
  private Boolean mainGroup;

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
    UserGroup userGroup = (UserGroup) object;
    return getId() != null && Objects.equals(getId(), userGroup.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer()
        .getPersistentClass().hashCode() : getClass().hashCode();
  }
}
