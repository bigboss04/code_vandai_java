package com.example.vandai.com.vn.services.product;

import com.example.vandai.com.vn.dtos.request.ProductRequest;
import com.example.vandai.com.vn.dtos.responses.product.ProductResponse;
import com.example.vandai.com.vn.exceptions.DataNotFoundException;
import com.example.vandai.com.vn.mapper.ProductMapper;
import com.example.vandai.com.vn.models.Category;
import com.example.vandai.com.vn.models.Product;
import com.example.vandai.com.vn.repositories.CategoryRepository;
import com.example.vandai.com.vn.repositories.ProductRepository;
import io.swagger.v3.oas.models.OpenAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
  private final CategoryRepository categoryRepository;
  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  @Override
  public ProductResponse createProduct(ProductRequest productRequest) throws DataNotFoundException {
    Category extingCategory = categoryRepository.findById(productRequest.getCategoryId())
            .orElseThrow(() -> new DataNotFoundException("Category not found"));

    Product newProduct = Product.builder()
            .name(productRequest.getName())
            .price(productRequest.getPrice())
            .thumbnail(productRequest.getThumbnail())
            .description(productRequest.getDescription())
            .category(extingCategory)
            .build();
      newProduct = productRepository.save(newProduct);
      return productMapper.toProductResponse(newProduct);
  }

  @Override
  public ProductResponse getProductById(Long productId) throws DataNotFoundException {
    Optional<Product>optionalProduct = productRepository.findById(productId);
    if (optionalProduct.isPresent()){
      return productMapper.toProductResponse(optionalProduct.get());
    }
    throw new DataNotFoundException("Product not found" + productId);
  }

  @Override
  @Transactional
  public void deleteProduct(Long productId) {
    Optional<Product> optionalProduct = productRepository.findById(productId);
    optionalProduct.ifPresent(productRepository::delete);
  }

  @Override
  public boolean existsByName(String name) {
    return productRepository.existsByName(name);
  }


}
