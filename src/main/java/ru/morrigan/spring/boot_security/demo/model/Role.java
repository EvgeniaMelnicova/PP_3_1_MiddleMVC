package ru.morrigan.spring.boot_security.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Data
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column
  private String name;

  //@ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
 // private Collection<User> users;

  @Override
  public String getAuthority() {
    return name;
  }

  // Попробуем так
  @Override
  public String toString() {
    return name;
  }
}
