package com.blogosphere.albums.photos.exception;

public class PhotoNotFoundException extends RuntimeException {

  private PhotoNotFoundException(String message) {
    super(message);
  }

  public static PhotoNotFoundException withId(String id) {
    return new PhotoNotFoundException(String.format("Photo with id '%s' not found", id));
  }
}
