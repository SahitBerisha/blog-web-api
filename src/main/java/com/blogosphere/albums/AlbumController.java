package com.blogosphere.albums;

import static org.springframework.http.HttpStatus.CREATED;

import com.blogosphere.albums.photos.PhotoRequest;
import com.blogosphere.albums.photos.PhotoResponse;
import com.blogosphere.albums.photos.PhotoService;
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
@Tag(name = "Albums & Photos")
@RequiredArgsConstructor
@RequestMapping("/api/v1/albums")
public class AlbumController {

  private final AlbumService service;
  private final PhotoService photoService;

  @PostMapping
  @Operation(summary = "Creates a new Album")
  public ResponseEntity<AlbumResponse> save(@Valid @RequestBody AlbumRequest request) {
    log.debug("REST request to create an Album : {}", request);
    var response = service.create(request);
    return ResponseEntity.status(CREATED).body(response);
  }

  @GetMapping
  @Operation(summary = "Returns all Albums")
  public ResponseEntity<List<AlbumResponse>> getAll() {
    log.debug("REST request to get all Albums");
    return ResponseEntity.ok(service.getAll());
  }

  @GetMapping("/{id}")
  @Operation(summary = "Returns an Album by Id")
  public ResponseEntity<AlbumResponse> getOne(@PathVariable String id) {
    log.debug("REST request to get an Album by id : {}", id);
    return ResponseEntity.ok(service.getOne(id));
  }

  @PutMapping("/{id}")
  @Operation(summary = "Updates an Album by Id")
  public ResponseEntity<AlbumResponse> update(@PathVariable String id,
                                              @Valid @RequestBody AlbumRequest request) {
    log.debug("REST request to update Album with id : {} - {}", id, request);
    return ResponseEntity.ok(service.update(id, request));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Deletes an Album by Id")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    log.debug("REST request to delete Album by id : {}", id);
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/{id}/photos")
  @Operation(summary = "Saves a Photo")
  public ResponseEntity<PhotoResponse> save(@PathVariable String id,
                                            @Valid @RequestBody PhotoRequest request) {
    log.debug("REST request to save a Photo for Album with id : {} : {}", id, request);
    return ResponseEntity.status(CREATED).body(photoService.save(id, request));
  }

  @GetMapping("/{id}/photos")
  @Operation(summary = "Returns all Photos for an Album")
  public ResponseEntity<List<PhotoResponse>> getAll(@PathVariable String id) {
    log.debug("REST request to get all Photos by Album id : {}", id);
    return ResponseEntity.ok(photoService.getAll(id));
  }

  @GetMapping("/{id}/photos/{photoId}")
  @Operation(summary = "Returns a Photo by Id")
  public ResponseEntity<PhotoResponse> getOne(@PathVariable String id,
                                              @PathVariable String photoId) {
    log.debug("REST request to get one Photo by Album id : {} : {}", id, photoId);
    return ResponseEntity.ok(photoService.getOne(id, photoId));
  }

  @PutMapping("/{id}/photos/{photoId}")
  @Operation(summary = "Updates a Photo by Id")
  public ResponseEntity<PhotoResponse> update(@PathVariable String id,
                                              @PathVariable String photoId,
                                              @Valid @RequestBody PhotoRequest request) {
    log.debug("REST request to update Photo for Album with id : {} : {} - {}", id, photoId, request);
    return ResponseEntity.ok(photoService.update(id, photoId, request));
  }

  @DeleteMapping("/{id}/photos/{photoId}")
  @Operation(summary = "Deletes a Photo by Id")
  public ResponseEntity<Void> delete(@PathVariable(name = "id") String id,
                                     @PathVariable(name = "photoId") String photoId) {
    log.debug("REST request to remove Photo by id : {} : {}", id, photoId);
    photoService.delete(id, photoId);
    return ResponseEntity.noContent().build();
  }
}
