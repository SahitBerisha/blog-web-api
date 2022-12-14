package com.blogosphere.posts;

import static com.blogosphere.security.SecurityUtil.currentLoggedInUser;
import static org.springframework.http.HttpStatus.CREATED;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.Instant;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Posts & Comments")
@RequestMapping("/api/v1")
public class PostController {

  private final PostService service;

  @PostMapping("/categories/{id}/posts")
  @Operation(summary = "Creates a new Post")
  public ResponseEntity<PostResponse> create(@PathVariable String id,
                                             @Valid @RequestBody PostRequest request) {
    log.debug("REST request to create a Post : {} : {}", id, request);
    return ResponseEntity.status(CREATED).body(service.create(id, request));
  }

  @GetMapping("/categories/{id}/posts")
  @Operation(summary = "Returns all Posts for a Category")
  public ResponseEntity<Page<PostResponse>> getAllByCategory(@PathVariable String id,
                                                             @Parameter(hidden = true) Pageable pageable) {
    log.debug("REST request to get all Posts for Category with id : {}", id);
    return ResponseEntity.ok(service.getAllByCategory(id, pageable));
  }

  @GetMapping("/posts")
  @Operation(summary = "Returns all Posts for a date range")
  public ResponseEntity<Page<PostResponse>> getAllByDateRange(@RequestParam
                                                              @Parameter(example = "2022-11-20T13:30:30Z")
                                                              @DateTimeFormat(iso = ISO.DATE_TIME) Instant from,
                                                              @RequestParam
                                                              @Parameter(example = "2022-11-20T14:30:30Z")
                                                              @DateTimeFormat(iso = ISO.DATE_TIME) Instant to,
                                                              @Parameter(hidden = true) Pageable pageable) {
    log.debug("REST request to get all Posts for date range : {} - {}", from, to);
    return ResponseEntity.ok(service.getAllByDateRange(from, to, pageable));
  }

  @GetMapping("/posts/latest")
  @Operation(summary = "Returns latest Posts for a User")
  public ResponseEntity<List<PostResponse>> getLatestPosts() {
    log.debug("REST request to get latest Posts for User : {}", currentLoggedInUser());
    return ResponseEntity.ok(service.getLatestPosts());
  }

  @GetMapping("/posts/{id}")
  @Operation(summary = "Returns a Post by Id")
  public ResponseEntity<PostResponse> getOne(@PathVariable String id) {
    log.debug("REST request to get one Post by id : {}", id);
    return ResponseEntity.ok(service.getOne(id));
  }

  @PutMapping("/posts/{id}")
  @Operation(summary = "Updates a Post by Id")
  public ResponseEntity<PostResponse> update(@PathVariable String id,
                                             @Valid @RequestBody PostRequest request) {
    log.debug("REST request to update Post with id : {} - {}", id, request);
    return ResponseEntity.ok(service.update(id, request));
  }

  @DeleteMapping("/posts/{id}")
  @Operation(summary = "Deletes a Post by Id")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    log.debug("REST request to delete Post with id : {}", id);
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}

