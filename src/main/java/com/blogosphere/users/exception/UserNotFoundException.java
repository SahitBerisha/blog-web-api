package com.blogosphere.users.exception;

public class UserNotFoundException extends RuntimeException {

  private UserNotFoundException(String message) {
    super(message);
  }

  public static UserNotFoundException withId(String id) {
    return new UserNotFoundException(String.format("User with id: %s could not be found", id));
  }

  public static UserNotFoundException withUsername(String username) {
    return new UserNotFoundException(String.format("User with username: %s could not be found", username));
  }
}
