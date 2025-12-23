package com.example.vandai.com.vn.mapper;

import com.example.vandai.com.vn.dtos.responses.product.ProductResponse;
import com.example.vandai.com.vn.models.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
  ProductResponse toProductResponse(Product product);
}
