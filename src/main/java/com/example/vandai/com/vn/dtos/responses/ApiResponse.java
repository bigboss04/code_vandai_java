package com.example.vandai.com.vn.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // Không hiển thị field null
public class ApiResponse<T> {
  private int statusCode;
  private String message;
  private T data;
  private List<String> errors;
  private Instant timestamp;
}
