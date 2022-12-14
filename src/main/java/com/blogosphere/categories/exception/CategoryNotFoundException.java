package com.blogosphere.categories.exception;

public class CategoryNotFoundException extends RuntimeException {

  private CategoryNotFoundException(String message) {
    super(message);
  }

  public static CategoryNotFoundException withId(String id) {
    return new CategoryNotFoundException(String.format("Category with id: '%s' not found", id));
  }
}
