package com.blogosphere;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.util.StringUtils.hasText;

import com.blogosphere.albums.exception.AlbumNotFoundException;
import com.blogosphere.albums.photos.exception.PhotoNotFoundException;
import com.blogosphere.categories.exception.CategoryNotFoundException;
import com.blogosphere.users.exception.UserNotFoundException;
import java.time.Instant;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

  private static final String NO_MESSAGE_AVAILABLE = "No message available";

  @ExceptionHandler({
      UserNotFoundException.class,
      CategoryNotFoundException.class,
      AlbumNotFoundException.class,
      PhotoNotFoundException.class
  })
  public ResponseEntity<ErrorResponse> handleNotFound(RuntimeException e) {
    log.error(e.getClass().getName(), e);
    return create(e, NOT_FOUND);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorResponse> handle(ConstraintViolationException e) {
    log.error(e.getClass().getName(), e);
    var message = e.getConstraintViolations().stream()
        .map(ConstraintViolation::getMessage)
        .collect(joining(", "));
    return create(message, BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleUnexpected(Exception e) {
    log.error(e.getClass().getName(), e);
    return create(e, INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException e) {
    log.error(e.getClass().getName(), e);
    var fieldErrors = e.getBindingResult().getAllErrors().stream()
        .map(this::buildMessage)
        .collect(joining(", "));
    var message = format("Validation failed for argument(s): %s", fieldErrors);
    return create(message, BAD_REQUEST);
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ErrorResponse> handle(BadCredentialsException e) {
    log.error(e.getClass().getName(), e);
    return create("Invalid username or password", BAD_REQUEST);
  }

  private String buildMessage(ObjectError objectError) {
    if (objectError instanceof FieldError) {
      return format("'%s': %s", ((FieldError) objectError).getField(), objectError.getDefaultMessage());
    }
    return format("'%s': %s", objectError.getObjectName(), objectError.getDefaultMessage());
  }

  private String getMessage(Exception exception) {
    if (hasText(exception.getLocalizedMessage())) {
      return exception.getLocalizedMessage();
    } else {
      return NO_MESSAGE_AVAILABLE;
    }
  }

  private ResponseEntity<ErrorResponse> create(Exception e, HttpStatus httpStatus) {
    return create(getMessage(e), httpStatus);
  }

  private ResponseEntity<ErrorResponse> create(String message, HttpStatus httpStatus) {
    var errorResponse = new ErrorResponse(
        httpStatus.value(),
        httpStatus.getReasonPhrase(),
        Instant.now(),
        message
    );
    return ResponseEntity.status(httpStatus).body(errorResponse);
  }

  record ErrorResponse(int code, String status, Instant timestamp, String message) {

  }
}
