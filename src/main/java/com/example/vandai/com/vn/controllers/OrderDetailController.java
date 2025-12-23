package com.example.vandai.com.vn.controllers;


import com.example.vandai.com.vn.dtos.request.OrderDetailRequest;
import com.example.vandai.com.vn.dtos.responses.ApiResponse;
import com.example.vandai.com.vn.dtos.responses.order.OrderDetailResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/order_details")
@RequiredArgsConstructor
@Slf4j
public class OrderDetailController {
    @PostMapping("")
    public ApiResponse<OrderDetailResponse> createOrderDetail(@Valid @RequestBody OrderDetailRequest orderDetailRequest) {
        // Implementation for creating an order detail
        return ApiResponse.<OrderDetailResponse>builder()
                .statusCode(200)
                .message("Order detail created successfully")
                .build();
    };


    @GetMapping("/{id}")
    public ApiResponse<OrderDetailResponse> getOrderDetailById(@PathVariable @Valid Long id) {
        // Implementation for retrieving order detail by ID
        return ApiResponse.<OrderDetailResponse>builder()
                .statusCode(200)
                .message("Get order detail by id successfully")
                .build();
    }
//get list order details by order id
    @GetMapping("/order/{orderId}")
    public ApiResponse<OrderDetailResponse> getOrderDetailsByOrderId(@PathVariable @Valid Long orderId) {
        // Implementation for retrieving order details by Order ID
        return ApiResponse.<OrderDetailResponse>builder()
                .statusCode(200)
                .message("Get order details by order id successfully")
                .build();
    }


    @PutMapping("/{id}")
    public ApiResponse<OrderDetailResponse> updateOrderDetail(
            @Valid @PathVariable Long id,
            @Valid @RequestBody OrderDetailRequest orderDetailRequest
    ) {
        // Implementation for updating order detail
        return ApiResponse.<OrderDetailResponse>builder()
                .statusCode(200)
                .message("Order detail updated successfully")
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteOrderDetail(@PathVariable @Valid Long id) {
        // Implementation for deleting order detail
        return ApiResponse.<Void>builder()
                .statusCode(200)
                .message("Order detail deleted successfully")
                .build();
    }


}
