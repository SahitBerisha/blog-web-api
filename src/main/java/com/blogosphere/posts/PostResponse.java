package com.blogosphere.posts;

import com.blogosphere.posts.tags.TagResponse;
import java.util.Set;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PostResponse {

  String id;
  String title;
  String body;
  String user;
  CategoryNameResponse category;
  Set<TagResponse> tags;
}

record CategoryNameResponse(String id, String name) {

}
