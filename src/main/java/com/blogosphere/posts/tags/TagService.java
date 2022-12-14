package com.blogosphere.posts.tags;

import com.blogosphere.posts.PostRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TagService {

  private final PostRepository postRepository;
  private final TagMapper mapper;

  @Transactional
  public TagResponse create(String id, TagRequest request) {
    var post = postRepository.findById(id).orElseThrow();
    var tag = mapper.map(request);
    post.addTag(tag);
    return mapper.map(tag);
  }

  @Transactional(readOnly = true)
  public TagResponse getOne(String id, String tagId) {
    var post = postRepository.findById(id).orElseThrow();
    var tag = post.getTagById(id);
    return mapper.map(tag);
  }

  @Transactional(readOnly = true)
  public List<TagResponse> getAll(String id) {
    var post = postRepository.findById(id).orElseThrow();
    return post.getTags().stream()
        .map(mapper::map)
        .toList();
  }

  @Transactional
  public TagResponse update(String id, String tagId, TagRequest request) {
    var post = postRepository.findById(id).orElseThrow();
    var tag = post.getTagById(tagId);
    mapper.map(tag, request);
    return mapper.map(tag);
  }

  @Transactional
  public void delete(String id, String tagId) {
    var post = postRepository.findById(id).orElseThrow();
    var tag = post.getTagById(tagId);
    post.removeTag(tag);
  }
}
