package com.blogosphere.posts.comments;

import com.blogosphere.core.AbstractEntity;
import com.blogosphere.posts.Post;
import com.blogosphere.users.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "comments")
public class Comment extends AbstractEntity {

  @Column(name = "body", nullable = false)
  private String body;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id")
  private Post post;
}
