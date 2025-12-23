package com.example.vandai.com.vn.controllers;

import com.example.vandai.com.vn.dtos.request.OrderRequest;
import com.example.vandai.com.vn.dtos.responses.ApiResponse;
import com.example.vandai.com.vn.dtos.responses.order.OrderResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/orders")
@Slf4j
public class OrderController {

    @PostMapping("")
    public ApiResponse<OrderResponse> createOrder(
            @RequestBody @Valid OrderRequest orderRequest, BindingResult result
    ) {
        if(result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
                  return ApiResponse.<OrderResponse>builder()
                            .message(String.join(";", errorMessages))
                            .statusCode(400)
                            .build();
        }
        // Implementation for creating an order
        return ApiResponse.<OrderResponse>builder()
                .statusCode(200)
                .message("Order created successfully")
                .build();
    };


    @GetMapping("/{user_id}")
    //Get http://localhost:9091/api/v1/orders/1
    public ApiResponse<OrderResponse> getOrderById(@PathVariable @Valid Long user_id) {
        return ApiResponse.<OrderResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Get order by id successfully")
                        .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<OrderResponse> updateOrder(
            @Valid @PathVariable Long id,
            @Valid @RequestBody OrderRequest orderRequest
    ) {
        // Implementation for updating order status
        return ApiResponse.<OrderResponse>builder()
                .statusCode(200)
                .message("Order status updated successfully")
                .build();
    }


    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteOrder( @PathVariable Long id) {
     return ApiResponse.<Void>builder()
             .statusCode(200)
             .message("Order deleted successfully")
             .build();
    }
}
