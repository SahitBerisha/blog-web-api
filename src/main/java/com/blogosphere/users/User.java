package com.blogosphere.users;

import com.blogosphere.albums.Album;
import com.blogosphere.core.AbstractEntity;
import com.blogosphere.core.Gender;
import com.blogosphere.posts.Post;
import com.blogosphere.posts.comments.Comment;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"username", "email", "phone_number"}))
public class User extends AbstractEntity {

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "username", nullable = false)
  private String username;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "date_of_birth")
  private LocalDate dateOfBirth;

  @Column(name = "gender")
  @Enumerated(EnumType.STRING)
  private Gender gender;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "website")
  private String website;

  @Embedded
  private Address address;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "users_roles",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
  private Set<Role> roles = new HashSet<>();

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
  private List<Album> albums = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
  private List<Post> posts = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
  private List<Comment> comments = new ArrayList<>();

  public User(String id) {
    super(id);
  }

  @Transient
  public String fullName() {
    return this.firstName + " " + this.lastName;
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
