package com.blogosphere.posts.comments;

import com.blogosphere.posts.Post;
import com.blogosphere.users.User;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

  public Comment map(CommentRequest request) {
    var comment = new Comment();
    map(comment, request);
    return comment;
  }

  public void map(Comment comment, CommentRequest request) {
    comment.setBody(request.body());
    comment.setUser(new User(request.userId()));
    comment.setPost(new Post(request.postId()));
  }

  public CommentResponse map(Comment comment) {
    return CommentResponse.builder()
        .id(comment.getId())
        .body(comment.getBody())
        .user(comment.getUser().fullName())
        .post(comment.getPost().getTitle())
        .build();
  }
}
