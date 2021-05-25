package com.assignment2.exceptions;

public class RuntimeExceptionCustom extends Exception {
  public static final long serialVersionUID = 43287433;

  /** Constructor taking custom message for custom exception. */
  public RuntimeExceptionCustom(final String message) {
    super(message);
  }
}
