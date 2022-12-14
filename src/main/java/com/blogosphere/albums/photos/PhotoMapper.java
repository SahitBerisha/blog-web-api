package com.blogosphere.albums.photos;

import org.springframework.stereotype.Component;

@Component
public class PhotoMapper {

  public Photo map(PhotoRequest request) {
    var photo = new Photo();
    map(photo, request);
    return photo;
  }

  public void map(Photo photo, PhotoRequest request) {
    photo.setTitle(request.title());
    photo.setUrl(request.url());
    photo.setThumbnailUrl(request.thumbnailUrl());
  }

  public PhotoResponse map(Photo photo) {
    return PhotoResponse.builder()
        .id(photo.getId())
        .title(photo.getTitle())
        .url(photo.getUrl())
        .thumbnailUrl(photo.getThumbnailUrl())
        .album(album(photo))
        .build();
  }

  private AlbumNameResponse album(Photo photo) {
    var album = photo.getAlbum();
    return new AlbumNameResponse(album.getId(), album.getTitle());
  }
}
