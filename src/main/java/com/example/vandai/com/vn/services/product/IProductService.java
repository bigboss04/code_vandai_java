package com.example.vandai.com.vn.services.product;

import com.example.vandai.com.vn.dtos.request.ProductRequest;
import com.example.vandai.com.vn.dtos.responses.product.ProductResponse;
import com.example.vandai.com.vn.exceptions.DataNotFoundException;

public interface IProductService{
  ProductResponse createProduct(ProductRequest productRequest) throws DataNotFoundException;
  ProductResponse getProductById(Long productId) throws DataNotFoundException;
  void deleteProduct(Long productId);
  boolean existsByName(String name);
}
