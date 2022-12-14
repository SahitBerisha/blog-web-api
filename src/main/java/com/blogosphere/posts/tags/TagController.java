package com.blogosphere.posts.tags;

import static org.springframework.http.HttpStatus.CREATED;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Tag(name = "Tag")
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts/{id}/tags")
public class TagController {

  private final TagService service;

  @PostMapping
  @Operation(summary = "Creates a new Tag")
  public ResponseEntity<TagResponse> create(@PathVariable String id,
                                            @Valid @RequestBody TagRequest request) {
    log.debug("REST request to create a Tag for Post with id : {} : {}", id, request);
    return ResponseEntity.status(CREATED).body(service.create(id, request));
  }

  @GetMapping
  @Operation(summary = "Returns all Tags for a Post")
  public ResponseEntity<List<TagResponse>> getAll(@PathVariable String id) {
    log.debug("REST request to get Tags for Post with id : {}", id);
    return ResponseEntity.ok(service.getAll(id));
  }

  @GetMapping("/{tagId}")
  @Operation(summary = "Returns a Tag by Id")
  public ResponseEntity<TagResponse> getOne(@PathVariable(name = "id") String id,
                                            @PathVariable(name = "tagId") String tagId) {
    log.debug("REST request to get a Tag by Post id : {} : {}", id, tagId);
    return ResponseEntity.ok(service.getOne(id, tagId));
  }

  @PutMapping("/{tagId}")
  @Operation(summary = "Updates a Tag by Id")
  public ResponseEntity<TagResponse> update(@PathVariable(name = "id") String id,
                                            @PathVariable(name = "tagId") String tagId,
                                            @Valid @RequestBody TagRequest request) {
    log.debug("REST request to update a Tag for Post with id : {} : {} - {}", id, tagId, request);
    return ResponseEntity.ok(service.update(id, tagId, request));
  }

  @DeleteMapping("/{tagId}")
  @Operation(summary = "Deletes a Tag by Id")
  public ResponseEntity<Void> delete(@PathVariable(name = "id") String id,
                                     @PathVariable(name = "tagId") String tagId) {
    log.debug("REST request to delete Tag by id : {} : {}", id, tagId);
    service.delete(id, tagId);
    return ResponseEntity.noContent().build();
  }
}
