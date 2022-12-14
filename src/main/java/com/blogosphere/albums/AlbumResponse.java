package com.blogosphere.albums;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AlbumResponse {

  String id;
  String user;
  String title;
  List<PhotoAlbumResponse> photos;
}

record PhotoAlbumResponse(String id, String title, String url) {

}
