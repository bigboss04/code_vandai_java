package com.example.vandai.com.vn.exceptions;

import jakarta.validation.constraints.NotEmpty;

public class DuplicateRequestException extends RuntimeException {
  public DuplicateRequestException(String message, @NotEmpty(message = "Category's name cannot be empty") String s) {
    super(message);
  }
}
