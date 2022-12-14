package com.blogosphere.albums.exception;

public class AlbumNotFoundException extends RuntimeException {

  private AlbumNotFoundException(String message) {
    super(message);
  }

  public static AlbumNotFoundException withId(String id) {
    return new AlbumNotFoundException(String.format("Album with id '%s' not found", id));
  }
}
