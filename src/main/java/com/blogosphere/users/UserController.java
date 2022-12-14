package com.blogosphere.users;

import static org.springframework.http.HttpStatus.CREATED;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.servlet.http.HttpServletRequest;
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
@Tag(name = "User")
@RequiredArgsConstructor
@SecurityRequirement(name = "blogosphere")
@RequestMapping("/api")
public class UserController {

  private final UserService service;

  @Hidden
  @PostMapping("/login")
  public ResponseEntity<TokenResponse> login(HttpServletRequest request) {
    log.debug("REST request to authenticate with username : {}", request.getParameter("username"));
    return ResponseEntity.ok(service.authenticate(request));
  }

  @PostMapping("/users")
  public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest request) {
    log.debug("REST request to create a User : {}", request);
    return ResponseEntity.status(CREATED).body(service.create(request));
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<UserResponse> getOne(@PathVariable String id) {
    log.debug("REST request to get a User with id : {}", id);
    return ResponseEntity.ok(service.getOne(id));
  }

  @PutMapping("/users/{id}")
  public ResponseEntity<UserResponse> update(@PathVariable String id,
                                             @Valid @RequestBody UserRequest request) {
    log.debug("REST request to update a User with id : {} - {}", id, request);
    return ResponseEntity.ok(service.update(id, request));
  }

  @DeleteMapping("/users/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    log.debug("REST request to delete a User");
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
