package com.blogosphere.categories;

import com.blogosphere.categories.exception.CategoryNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository repository;
  private final CategoryMapper mapper;

  @Transactional
  public CategoryResponse create(CategoryRequest request) {
    var category = repository.save(mapper.map(request));
    return mapper.map(category);
  }

  @Transactional(readOnly = true)
  public List<CategoryResponse> getAll() {
    return repository.findAll().stream()
        .map(mapper::map)
        .toList();
  }

  @Transactional(readOnly = true)
  public CategoryResponse getOne(String id) {
    var category = findCategory(id);
    return mapper.map(category);
  }

  @Transactional
  public CategoryResponse update(String id, CategoryRequest request) {
    var category = findCategory(id);
    mapper.map(category, request);
    return mapper.map(category);
  }

  @Transactional
  public void delete(String id) {
    repository.deleteById(id);
  }

  private Category findCategory(String id) {
    return repository.findById(id)
        .orElseThrow(() -> CategoryNotFoundException.withId(id));
  }
}
