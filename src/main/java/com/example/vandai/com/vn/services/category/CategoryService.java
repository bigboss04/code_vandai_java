package com.example.vandai.com.vn.services.category;

import com.example.vandai.com.vn.dtos.request.CategoryRequest;
import com.example.vandai.com.vn.dtos.responses.CategoryResponse;
import com.example.vandai.com.vn.exceptions.DataNotFoundException;
import com.example.vandai.com.vn.exceptions.DuplicateRequestException;
import com.example.vandai.com.vn.mapper.CategoryMapper;
import com.example.vandai.com.vn.models.Category;
import com.example.vandai.com.vn.models.Product;
import com.example.vandai.com.vn.repositories.CategoryRepository;
import com.example.vandai.com.vn.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
  import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CategoryService implements ICategoryService {
  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;
  private final CategoryMapper categoryMapper;


  @Override
  public CategoryResponse createCategory(CategoryRequest categoryRequest) {
    validateCreateCategory(categoryRequest);
    Category category = categoryMapper.toEntity(categoryRequest);
    Category savedCategory = persist(category);
    return categoryMapper.toCategoryResponse(savedCategory);
  }

//  private methods
  private void validateCreateCategory(CategoryRequest categoryRequest) {
    // Add validation logic here if needed
    if(categoryRepository.existsByName(categoryRequest.getName())){
      throw new DuplicateRequestException("Category name already exists",
              "Category already exists with name: " + categoryRequest.getName()
      );
    }
  }

  private Category persist(Category category){
    try{
      Category savedCategory = categoryRepository.save(category);
      log.info("Category saved successfully: {}", savedCategory);
      return savedCategory;
    } catch (Exception e) {
      log.error("Error saving category: {}", e.getMessage());
      throw new ServiceException(
              "CREATE_CATEGORY_FAILED"
      );
    }
  }

  @Override
  public CategoryResponse getCategoryById(Long categoryId) throws DataNotFoundException {
    Category extingCategory =  categoryRepository.findById(categoryId).
            orElseThrow(() -> new DataNotFoundException("Category not found"));
    return  categoryMapper.toCategoryResponse(extingCategory);
  }

  @Override
  public List<CategoryResponse> getAllCategories() {
    return categoryMapper.toCategoryResponseList(categoryRepository.findAll());
  }

  @Override
  public CategoryResponse updateCategory(Long categoryId, CategoryRequest category) throws DataNotFoundException {
    Category existingCategory = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new DataNotFoundException("Category not found"));
    existingCategory.setName(category.getName());
    existingCategory = categoryRepository.save(existingCategory);
    return categoryMapper.toCategoryResponse(existingCategory);
  }

  @Override
  @Transactional
  public CategoryResponse deleteCategory(Long categoryId) throws DataNotFoundException {
    Category existingCategory = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new DataNotFoundException("Category not found"));

  List<Product> products = productRepository.findByCategory(categoryId);
  if(!products.isEmpty()){
  throw new IllegalStateException("Cannot delete category with associated products");
  }
  else{
    categoryRepository.delete(existingCategory);
    return categoryMapper.toCategoryResponse(existingCategory);
  }
  }
}
