package com.example.test.common.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import javax.validation.ConstraintViolationException;

import com.example.test.utils.ApiUtils.ApiResult;
import static com.example.test.utils.ApiUtils.error;

@ControllerAdvice
public class GeneralExceptionHandler {
  private final Logger log = LoggerFactory.getLogger(getClass());

  private ResponseEntity<ApiResult<?>> newResponse(Throwable throwable, HttpStatus status) {
    return newResponse(throwable.getMessage(), status);
  }

  private ResponseEntity<ApiResult<?>> newResponse(String message, HttpStatus status) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return new ResponseEntity<>(error(message, status), headers, status);
  }
  
  @ExceptionHandler({
    NoHandlerFoundException.class,
    NotFoundException.class
  })
  public ResponseEntity<?> handleNotFoundException(Exception e) {
    return newResponse(e, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({
    UnauthorizedException.class,
    MissingRequestHeaderException.class
  })
  public ResponseEntity<?> handleUnauthorizedException(Exception e) {
    return newResponse(e, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler({
    IllegalArgumentException.class,
    IllegalStateException.class,
    ConstraintViolationException.class,
    MethodArgumentNotValidException.class,
    InvalidConditionException.class
  })
  public ResponseEntity<?> handleBadRequestException(Exception e) {
    return newResponse(e, HttpStatus.BAD_REQUEST);
  }
  
  @ExceptionHandler(HttpMediaTypeException.class)
  public ResponseEntity<?> handleHttpMediaTypeException(Exception e) {
    return newResponse(e, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<?> handleMethodNotAllowedException(Exception e) {
    return newResponse(e, HttpStatus.METHOD_NOT_ALLOWED);
  }

  @ExceptionHandler({Exception.class, RuntimeException.class})
  public ResponseEntity<?> handleException(Exception e) {
    log.error("Unexpected exception occurred: {}", e.getMessage(), e);
    return newResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
