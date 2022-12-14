package com.blogosphere.albums;

import static com.blogosphere.security.SecurityUtil.currentLoggedInUser;

import com.blogosphere.albums.exception.AlbumNotFoundException;
import com.blogosphere.users.User;
import com.blogosphere.users.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AlbumService {

  private final AlbumRepository repository;
  private final UserRepository userRepository;
  private final AlbumMapper mapper;

  @Transactional
  public AlbumResponse create(AlbumRequest request) {
    var album = repository.save(mapper.map(request));
    return mapper.map(album);
  }

  @Transactional(readOnly = true)
  public AlbumResponse getOne(String id) {
    var album = findAlbum(id);
    return mapper.map(album);
  }

  @Transactional(readOnly = true)
  public List<AlbumResponse> getAll() {
    return repository.findAllByUserId(user().getId()).stream()
        .map(mapper::map)
        .toList();
  }

  @Transactional
  public AlbumResponse update(String id, AlbumRequest request) {
    var album = findAlbum(id);
    mapper.map(album, request);
    return mapper.map(album);
  }

  @Transactional
  public void delete(String id) {
    repository.deleteById(id);
  }

  private Album findAlbum(String id) {
    return repository.findById(id)
        .orElseThrow(() -> AlbumNotFoundException.withId(id));
  }

  private User user() {
    var username = currentLoggedInUser();
    return userRepository.findByUsername(username).orElseThrow();
  }
}
