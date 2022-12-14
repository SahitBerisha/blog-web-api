package com.blogosphere.categories;

import com.blogosphere.posts.PostMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryMapper {

  private final PostMapper postMapper;

  public Category map(CategoryRequest request) {
    var category = new Category();
    map(category, request);
    return category;
  }

  public void map(Category category, CategoryRequest request) {
    category.setName(request.name());
  }

  public CategoryResponse map(Category category) {
    return CategoryResponse.builder()
        .id(category.getId())
        .name(category.getName())
        .posts(getPosts(category))
        .build();
  }

  private List<PostTitleResponse> getPosts(Category category) {
    return category.getPosts().stream()
        .map(post -> new PostTitleResponse(post.getId(), post.getTitle()))
        .toList();
  }
}
