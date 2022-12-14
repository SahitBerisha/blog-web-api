package com.blogosphere.posts;

import static com.blogosphere.security.SecurityUtil.currentLoggedInUser;

import com.blogosphere.categories.CategoryRepository;
import com.blogosphere.users.User;
import com.blogosphere.users.UserRepository;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

  private final UserRepository userRepository;
  private final CategoryRepository categoryRepository;
  private final PostRepository repository;
  private final PostMapper mapper;

  @Transactional
  public PostResponse create(String categoryId, PostRequest request) {
    var category = categoryRepository.findById(categoryId).orElseThrow();
    var post = mapper.map(request, category, user());
    repository.save(post);
    return mapper.map(post);
  }

  @Transactional(readOnly = true)
  public Page<PostResponse> getAllByCategory(String categoryId, Pageable pageable) {
    return repository.findAllByCategoryId(categoryId, pageable)
        .map(mapper::map);
  }

  @Transactional(readOnly = true)
  public Page<PostResponse> getAllByDateRange(Instant from, Instant to, Pageable pageable) {
    return repository.findAllByDateRange(user().getId(), from, to, pageable)
        .map(mapper::map);
  }

  @Transactional(readOnly = true)
  public PostResponse getOne(String id) {
    var post = repository.findById(id).orElseThrow();
    return mapper.map(post);
  }

  @Transactional(readOnly = true)
  public List<PostResponse> getLatestPosts() {
    return repository.findLastThreePosts(user().getId(), PageRequest.of(0, 3)).stream()
        .map(mapper::map)
        .toList();
  }

  @Transactional
  public PostResponse update(String id, PostRequest request) {
    var post = repository.findById(id).orElseThrow();
    mapper.map(post, request);
    return mapper.map(post);
  }

  @Transactional
  public void delete(String id) {
    repository.deleteById(id);
  }

  private User user() {
    var username = currentLoggedInUser();
    return userRepository.findByUsername(username).orElseThrow();
  }
}
