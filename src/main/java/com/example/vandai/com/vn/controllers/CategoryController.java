package com.example.vandai.com.vn.controllers;


import com.example.vandai.com.vn.dtos.request.CategoryRequest;
import com.example.vandai.com.vn.dtos.responses.ApiResponse;
import com.example.vandai.com.vn.dtos.responses.CategoryResponse;
import com.example.vandai.com.vn.exceptions.DataNotFoundException;
import com.example.vandai.com.vn.services.category.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/categories")
public class CategoryController {
  private final CategoryService categoryService;
  @PostMapping("")
  public ApiResponse<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
    CategoryResponse categoryResponse = categoryService.createCategory(categoryRequest);
    return ApiResponse.<CategoryResponse>builder()
            .statusCode(HttpStatus.OK.value())
            .data(categoryResponse)
            .message("Create category successfully")
            .build();
  }


  @GetMapping("/{id}")
  public ApiResponse<CategoryResponse> getCategoryById(@PathVariable Long id) throws DataNotFoundException {
    // Implementation for retrieving category by ID
    return ApiResponse.<CategoryResponse>builder()
                    .statusCode(HttpStatus.OK.value())
                    .data(categoryService.getCategoryById(id))
                    .message("Get category by id successfully")
                    .build();
  }
}
