package com.blogosphere.albums.photos;

import com.blogosphere.albums.Album;
import com.blogosphere.albums.AlbumRepository;
import com.blogosphere.albums.exception.AlbumNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PhotoService {

  private final AlbumRepository repository;
  private final PhotoMapper mapper;

  @Transactional
  public PhotoResponse save(String id, PhotoRequest request) {
    var album = findAlbum(id);
    var photo = mapper.map(request);
    album.addPhoto(photo);
    return mapper.map(photo);
  }

  @Transactional(readOnly = true)
  public PhotoResponse getOne(String id, String photoId) {
    var album = findAlbum(id);
    var photo = album.getPhotoById(photoId);
    return mapper.map(photo);
  }

  @Transactional(readOnly = true)
  public List<PhotoResponse> getAll(String id) {
    var album = findAlbum(id);
    return album.getPhotos().stream()
        .map(mapper::map)
        .toList();
  }

  @Transactional
  public PhotoResponse update(String id, String photoId, PhotoRequest request) {
    var album = findAlbum(id);
    var photo = album.getPhotoById(photoId);
    mapper.map(photo, request);
    return mapper.map(photo);
  }

  @Transactional
  public void delete(String id, String photoId) {
    var album = findAlbum(id);
    var photo = album.getPhotoById(photoId);
    album.removePhoto(photo);
  }

  private Album findAlbum(String id) {
    return repository.findById(id)
        .orElseThrow(() -> AlbumNotFoundException.withId(id));
  }
}
