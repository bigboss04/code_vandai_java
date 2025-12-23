package com.example.vandai.com.vn.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "tbl_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j(topic = "product")
@Builder
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "Name is required")
  private String name;

  @NotNull(message = "Price is required")
  @Min(value = 0, message = "Price must the greater than equal to 0")
  @Max(value = 100000 , message ="Price must less than or equal to 10,000,000")
  private Float price;

  private String thumbnail;

  private String description;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;
}
