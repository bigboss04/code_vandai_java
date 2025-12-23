package com.example.vandai.com.vn.mapper;

import com.example.vandai.com.vn.dtos.request.CategoryRequest;
import com.example.vandai.com.vn.dtos.responses.CategoryResponse;
import com.example.vandai.com.vn.models.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
  Category toEntity(CategoryRequest categoryRequest);
  CategoryResponse toCategoryResponse(Category category);
  List<CategoryResponse> toCategoryResponseList(List<Category> categories);
}
