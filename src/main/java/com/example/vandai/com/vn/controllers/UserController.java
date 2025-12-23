package com.example.vandai.com.vn.controllers;


import com.example.vandai.com.vn.dtos.request.ProductRequest;
import com.example.vandai.com.vn.dtos.request.UserLoginRequest;
import com.example.vandai.com.vn.dtos.request.UserRequest;
import com.example.vandai.com.vn.dtos.responses.ApiResponse;
import com.example.vandai.com.vn.dtos.responses.product.ProductResponse;
import com.example.vandai.com.vn.dtos.responses.user.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Arrays.stream;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/user")
@Slf4j
public class UserController {
    @PostMapping("/register")
    public ApiResponse<UserResponse> createUser(
            @Valid @RequestBody UserRequest userRequest,
            BindingResult result) throws Exception {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ApiResponse.<UserResponse>builder()
                    .statusCode(400)
                    .message(String.join(", ", errors))
                    .build();
        }

        if(!userRequest.getPassword().equals(userRequest.getRetypePassword())){
            return ApiResponse.<UserResponse>builder()
                    .statusCode(400)
                    .message("Password and Confirm Password do not match")
                    .build();
        }
        return ApiResponse.<UserResponse>builder()
                .statusCode(200)
                .message("User registered successfully")
                .build();
    };




    @PostMapping("/login")
    public ApiResponse<UserResponse> loginUser(
            @Valid @RequestBody UserLoginRequest userRequest,
            BindingResult result) throws Exception {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ApiResponse.<UserResponse>builder()
                    .statusCode(400)
                    .message(String.join(", ", errors))
                    .build();
        }
        return ApiResponse.<UserResponse>builder()
                .statusCode(200)
                .message("User logged in successfully")
                .build();
    }
}
