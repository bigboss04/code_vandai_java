package com.example.vandai.com.vn.dtos.responses.product;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
  private Long id;
  private String name;
  private Float price;
  private String thumbnail;
  private String description;
  private int totalPages;
}
