package com.example.vandai.com.vn.services.category;

import com.example.vandai.com.vn.dtos.request.CategoryRequest;
import com.example.vandai.com.vn.dtos.responses.CategoryResponse;
import com.example.vandai.com.vn.exceptions.DataNotFoundException;

import java.util.List;

public interface ICategoryService {
      CategoryResponse createCategory(CategoryRequest category);
      CategoryResponse getCategoryById(Long categoryId) throws DataNotFoundException;
      List<CategoryResponse> getAllCategories();
      CategoryResponse updateCategory(Long categoryId, CategoryRequest category) throws DataNotFoundException;
      CategoryResponse deleteCategory(Long categoryId) throws DataNotFoundException;
}
