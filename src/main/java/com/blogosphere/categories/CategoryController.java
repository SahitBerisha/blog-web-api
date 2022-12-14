package com.blogosphere.categories;

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
@RequiredArgsConstructor
@Tag(name = "Categories")
@RequestMapping("/api/v1/categories")
public class CategoryController {

  private final CategoryService service;

  @PostMapping
  @Operation(summary = "Creates a new Category")
  public ResponseEntity<CategoryResponse> save(@Valid @RequestBody CategoryRequest request) {
    log.debug("REST request to create a Category : {}", request);
    var category = service.create(request);
    return ResponseEntity.status(CREATED).body(category);
  }

  @GetMapping
  @Operation(summary = "Returns all Categories")
  public ResponseEntity<List<CategoryResponse>> getAll() {
    log.debug("REST request to get all Categories");
    return ResponseEntity.ok(service.getAll());
  }

  @GetMapping("/{id}")
  @Operation(summary = "Returns a Category by Id")
  public ResponseEntity<CategoryResponse> getOne(@PathVariable String id) {
    log.debug("REST request to get a Category by id : {}", id);
    return ResponseEntity.ok(service.getOne(id));
  }

  @PutMapping("/{id}")
  @Operation(summary = "Updates a Category by Id")
  public ResponseEntity<CategoryResponse> update(@PathVariable String id,
                                                 @Valid @RequestBody CategoryRequest request) {
    log.debug("REST request to update a Category by id : {}, {}", id, request);
    return ResponseEntity.ok(service.update(id, request));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Deletes a Category by Id")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    log.debug("REST request to delete a Category by id : {}", id);
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
