package com.blogosphere.posts;

import com.blogosphere.categories.Category;
import com.blogosphere.posts.tags.TagMapper;
import com.blogosphere.posts.tags.TagResponse;
import com.blogosphere.users.User;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostMapper {

  private final TagMapper tagMapper;

  public Post map(PostRequest request, Category category, User user) {
    var post = new Post();
    map(post, request);
    post.setCategory(category);
    post.setUser(user);
    return post;
  }

  public void map(Post post, PostRequest request) {
    post.setTitle(request.title());
    post.setBody(request.body());
  }

  public PostResponse map(Post post) {
    return PostResponse.builder()
        .id(post.getId())
        .title(post.getTitle())
        .body(post.getBody())
        .user(post.getUser().fullName())
        .category(category(post))
        .tags(tags(post))
        .build();
  }

  private CategoryNameResponse category(Post post) {
    var category = post.getCategory();
    return new CategoryNameResponse(category.getId(), category.getName());
  }

  private Set<TagResponse> tags(Post post) {
    return post.getTags().stream()
        .map(tagMapper::map)
        .collect(Collectors.toUnmodifiableSet());
  }
}
