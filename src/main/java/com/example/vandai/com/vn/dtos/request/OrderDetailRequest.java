package com.example.vandai.com.vn.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailRequest {

    @NotNull(message = "order_id is required")
    @Positive(message = "order_id must be greater than 0")
    @JsonProperty("order_id")
    private Long orderId;

    @NotNull(message = "product_id is required")
    @Positive(message = "product_id must be greater than 0")
    @JsonProperty("product_id")
    private Long productId;

    @NotNull(message = "price is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "price must be >= 0")
    private BigDecimal price;

    @Positive(message = "number_of_products must be >= 1")
    @JsonProperty("number_of_products")
    private Integer numberOfProducts;

    @NotNull(message = "total_money is required")
    @JsonProperty("total_money")
    private BigDecimal totalMoney;

    @Size(max = 50, message = "color must not exceed 50 characters")
    private String color;
}
