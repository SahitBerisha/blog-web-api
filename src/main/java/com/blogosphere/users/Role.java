package com.blogosphere.users;

import com.blogosphere.core.AbstractEntity;
import com.blogosphere.core.RoleName;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "roles")
public class Role extends AbstractEntity {

  @Enumerated(EnumType.STRING)
  @Column(name = "name", nullable = false)
  private RoleName name;

  public Role(RoleName name) {
    this.name = name;
  }
}
