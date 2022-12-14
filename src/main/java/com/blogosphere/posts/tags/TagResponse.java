package com.blogosphere.posts.tags;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TagResponse {

  String id;
  String name;
}
