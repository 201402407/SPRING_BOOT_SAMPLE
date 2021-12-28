package com.example.test.common.exceptions;

public class InvalidConditionException extends RuntimeException {

  public InvalidConditionException(String message) {
    super(message);
  }

  public InvalidConditionException(String message, Throwable cause) {
    super(message, cause);
  }

}