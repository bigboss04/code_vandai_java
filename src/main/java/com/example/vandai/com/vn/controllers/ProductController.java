package com.example.vandai.com.vn.controllers;
import com.example.vandai.com.vn.dtos.request.ProductRequest;
import com.example.vandai.com.vn.dtos.responses.ApiResponse;
import com.example.vandai.com.vn.dtos.responses.product.ProductResponse;
import com.example.vandai.com.vn.utils.FileUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/product")
@Slf4j
public class ProductController {

  @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ApiResponse<ProductResponse> createProduct(
          @Valid @ModelAttribute ProductRequest productRequest,
//          @RequestParam("file") MultipartFile file,
          BindingResult result) throws Exception {
    if (result.hasErrors()) {
      List<String> errors = result.getFieldErrors()
              .stream()
              .map(FieldError::getDefaultMessage)
              .toList();
      return ApiResponse.<ProductResponse>builder()
              .statusCode(400)
              .message(String.join(", ", errors))
              .build();
    }

    List<MultipartFile> files = productRequest.getFiles();
    files = files == null ? new ArrayList<MultipartFile>() : files;
    for(MultipartFile file: files){
      if (file != null && !file.isEmpty()) {
        // check size file and dinh dang
        if (file.getSize() > 10 * 1024 * 1024) {
          if (file.getSize() == 0) {
            continue;
          }
          return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                  .body(ApiResponse.<ProductResponse>builder()
                          .statusCode(413)
                          .message("File size exceeds the maximum limit of 10MB")
                          .build()).getBody();
        }
//      check content type
        String contentType = file.getContentType();
        if (contentType == null || !(contentType.startsWith("image/") )) {
          return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                  .body(ApiResponse.<ProductResponse>builder()
                          .statusCode(415)
                          .message("Only JPEG and PNG image formats are supported")
                          .build()).getBody();
        }
      }
      String fileName = FileUtils.storeFile(file);
      // save product vao product_image
    }

    //save file and update thumbnail in database
    return ApiResponse.<ProductResponse>builder()
            .statusCode(HttpStatus.CREATED.value())
            .message("Create product successfully")
          .build();
  }

  @GetMapping("")
  public ApiResponse<ProductResponse> getProducts(
          @RequestParam(defaultValue = "0") int page,
          @RequestParam(defaultValue = "10") int size) {
    return ApiResponse.<ProductResponse>builder()
            .statusCode(200)
            .message("Get products successfully")
            .build();
  }

  @GetMapping("/{id}")
  public ApiResponse<String> getProductById(@RequestParam Long id) {
    return ApiResponse.<String>builder()
            .statusCode(200)
            .message("Get product detail successfully")
            .build();
  }

  @DeleteMapping("/{id}")
  public ApiResponse<String> deleteProduct(@PathVariable Long id) {
    return ApiResponse.<String>builder()
            .statusCode(200)
            .message("Delete product successfully")
            .build();
  }
}
