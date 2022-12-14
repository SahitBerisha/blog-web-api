package com.blogosphere.categories;

import com.blogosphere.core.AbstractEntity;
import com.blogosphere.posts.Post;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "categories")
public class Category extends AbstractEntity {

  @Column(name = "name", nullable = false)
  private String name;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "category", orphanRemoval = true)
  private List<Post> posts = new ArrayList<>();

  public Category(String id) {
    super(id);
  }
}
