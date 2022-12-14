package com.blogosphere.posts.tags;

import com.blogosphere.core.AbstractEntity;
import com.blogosphere.posts.Post;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tags")
public class Tag extends AbstractEntity {

  private String name;

  @ManyToMany(fetch = FetchType.LAZY,
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      })
  @JoinTable(name = "posts_tags",
      joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
  private List<Post> posts = new ArrayList<>();
}
