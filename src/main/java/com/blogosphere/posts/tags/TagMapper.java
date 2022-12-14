package com.blogosphere.posts.tags;

import org.springframework.stereotype.Component;

@Component
public class TagMapper {

  public Tag map(TagRequest request) {
    var tag = new Tag();
    map(tag, request);
    return tag;
  }

  public void map(Tag tag, TagRequest request) {
    tag.setName(request.name());
  }

  public TagResponse map(Tag tag) {
    return TagResponse.builder()
        .id(tag.getId())
        .name(tag.getName())
        .build();
  }
}
