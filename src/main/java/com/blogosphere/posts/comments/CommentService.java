package com.blogosphere.posts.comments;

import com.blogosphere.posts.Post;
import com.blogosphere.posts.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

  private final PostRepository repository;
  private final CommentMapper mapper;

  @Transactional
  public CommentResponse create(String id, CommentRequest request) {
    var post = getPost(id);
    var comment = mapper.map(request);
    post.addComment(comment);
    return mapper.map(comment);
  }

  @Transactional(readOnly = true)
  public CommentResponse getOne(String id, String commentId) {
    var post = getPost(id);
    var comment = post.getCommentById(commentId);
    return mapper.map(comment);
  }

  @Transactional(readOnly = true)
  public Page<CommentResponse> getAll(String id, Pageable pageable) {
    var post = getPost(id);
    var comments = post.getComments().stream()
        .map(mapper::map)
        .toList();
    return new PageImpl<>(comments, pageable, comments.size());
  }

  @Transactional
  public CommentResponse update(String id, String commentId, CommentRequest request) {
    var post = getPost(id);
    var comment = post.getCommentById(commentId);
    mapper.map(comment, request);
    return mapper.map(comment);
  }

  @Transactional
  public void delete(String id, String commentId) {
    var post = getPost(id);
    var comment = post.getCommentById(commentId);
    post.removeComment(comment);
  }

  private Post getPost(String id) {
    return repository.findById(id).orElseThrow();
  }
}
