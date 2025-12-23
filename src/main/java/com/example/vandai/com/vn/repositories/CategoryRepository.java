package com.example.vandai.com.vn.repositories;

import com.example.vandai.com.vn.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  boolean existsByName(String name);
}
