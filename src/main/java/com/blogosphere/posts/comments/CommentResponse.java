package com.blogosphere.posts.comments;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CommentResponse {

  String id;
  String body;
  String user;
  String post;
}
