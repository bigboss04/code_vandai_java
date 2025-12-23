package com.example.vandai.com.vn.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data//toString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
  @NotEmpty(message = "Category's is name not blank")
  @Size(min = 3 , max = 100, message = "Name must be between 3 and 100 charter")
  @NotNull
  private String name;

  @NotNull(message = "Price is required")
  @Min(value = 0, message = "Price must the greater than equal to 0")
  @Max(value = 100000 , message ="Price must less than or equal to 10,000,000")
  private Float price;

  private String thumbnail;

  private String description;

  @NotNull(message = "Category ID is required")
  @JsonProperty("category_id")
  private Long categoryId;

  private List<MultipartFile> files;
}
