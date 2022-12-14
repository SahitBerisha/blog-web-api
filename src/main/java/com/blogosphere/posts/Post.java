package com.blogosphere.posts;

import com.blogosphere.categories.Category;
import com.blogosphere.core.AbstractEntity;
import com.blogosphere.posts.comments.Comment;
import com.blogosphere.posts.tags.Tag;
import com.blogosphere.users.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "posts")
public class Post extends AbstractEntity {

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "body")
  private String body;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private Category category;

  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Comment> comments = new ArrayList<>();

  @ManyToMany(fetch = FetchType.LAZY,
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      })
  @JoinTable(name = "posts_tags",
      joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
  private Set<Tag> tags = new HashSet<>();

  public Post(String id) {
    super(id);
  }

  @Transient
  public void addComment(Comment comment) {
    comment.setPost(this);
    this.comments.add(comment);
  }

  @Transient
  public Comment getCommentById(String id) {
    return this.comments.stream()
        .filter(comment -> comment.getId().equals(id))
        .findFirst()
        .orElseThrow();
  }

  @Transient
  public void removeComment(Comment comment) {
    this.comments.remove(comment);
  }

  @Transient
  public void addTag(Tag tag) {
    tag.getPosts().add(this);
    this.tags.add(tag);
  }

  @Transient
  public Tag getTagById(String id) {
    return this.tags.stream()
        .filter(tag -> tag.getId().equals(id))
        .findFirst()
        .orElseThrow();
  }

  @Transient
  public void removeTag(Tag tag) {
    this.tags.remove(tag);
  }
}
