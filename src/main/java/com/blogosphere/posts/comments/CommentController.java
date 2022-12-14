package com.blogosphere.posts.comments;

import static org.springframework.http.HttpStatus.CREATED;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Posts & Comments")
@RequestMapping("/api/v1/posts/{id}/comments")
public class CommentController {

  private final CommentService service;

  @PostMapping
  @Operation(summary = "Creates a new Comment for a Post")
  public ResponseEntity<CommentResponse> create(@PathVariable String id,
                                                @Valid @RequestBody CommentRequest request) {
    log.debug("REST request to create a Comment for Post with id : {} : {}", id, request);
    return ResponseEntity.status(CREATED).body(service.create(id, request));
  }

  @GetMapping
  @Operation(summary = "Returns all Comments for a Post")
  public ResponseEntity<Page<CommentResponse>> getAll(@PathVariable String id,
                                                      @Parameter(hidden = true) Pageable pageable) {
    log.debug("REST request to get Comments for Post with id : {}", id);
    return ResponseEntity.ok(service.getAll(id, pageable));
  }

  @GetMapping("/{commentId}")
  @Operation(summary = "Returns a Comment by Id")
  public ResponseEntity<CommentResponse> getOne(@PathVariable(name = "id") String id,
                                                @PathVariable(name = "commentId") String commentId) {
    log.debug("REST request to get a Comment by Post id : {} : {}", id, commentId);
    return ResponseEntity.ok(service.getOne(id, commentId));
  }

  @PutMapping("/{commentId}")
  @Operation(summary = "Updates a Comment by Id")
  public ResponseEntity<CommentResponse> update(@PathVariable(name = "id") String id,
                                                @PathVariable(name = "commentId") String commentId,
                                                @Valid @RequestBody CommentRequest request) {
    log.debug("REST request to update a Comment for Post with id : {} : {} - {}", id, commentId, request);
    return ResponseEntity.ok(service.update(id, commentId, request));
  }

  @DeleteMapping("/{commentId}")
  @Operation(summary = "Deletes a Comment by Id")
  public ResponseEntity<Void> delete(@PathVariable(name = "id") String id,
                                     @PathVariable(name = "commentId") String commentId) {
    log.debug("REST request to delete Comment by id : {} : {}", id, commentId);
    service.delete(id, commentId);
    return ResponseEntity.noContent().build();
  }
}
