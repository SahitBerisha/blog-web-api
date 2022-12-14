package com.blogosphere.albums;

import com.blogosphere.users.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlbumMapper {

  public Album map(AlbumRequest request) {
    var album = new Album();
    map(album, request);
    return album;
  }

  public void map(Album album, AlbumRequest request) {
    album.setUser(new User(request.userId()));
    album.setTitle(request.title());
  }

  public AlbumResponse map(Album album) {
    return AlbumResponse.builder()
        .id(album.getId())
        .title(album.getTitle())
        .user(album.getUser().fullName())
        .photos(getPhotos(album))
        .build();
  }

  private List<PhotoAlbumResponse> getPhotos(Album album) {
    return album.getPhotos().stream()
        .map(a -> new PhotoAlbumResponse(a.getId(), a.getTitle(), a.getUrl()))
        .toList();
  }
}
