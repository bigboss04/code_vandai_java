package com.example.vandai.com.vn.repositories;

import com.example.vandai.com.vn.models.Category;
import com.example.vandai.com.vn.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
  boolean existsByName(String name);
  List<Product> findByCategory(Long categoryId);

}
