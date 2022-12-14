package com.blogosphere.albums.photos;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PhotoResponse {

  String id;
  String title;
  String url;
  String thumbnailUrl;
  AlbumNameResponse album;
}

record AlbumNameResponse(String id, String name) {

}
