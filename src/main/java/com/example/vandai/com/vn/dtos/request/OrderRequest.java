package com.example.vandai.com.vn.dtos.request;


import com.example.vandai.com.vn.enums.PaymentMethod;
import com.example.vandai.com.vn.enums.ShippingMethod;
import com.example.vandai.com.vn.validation.phoneCheck.PhoneNumberValid;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    @JsonProperty("user_id")
    @Min(value = 1, message = "User's ID must be > 0")
    private Long userId;

    @JsonProperty("full_name")
    @NotBlank(message = "Full name is required")
    private String fullName;

    @Email(message = "Email is not valid")
    private String email;

    @JsonProperty("phone_number")
    @NotBlank(message = "Phone number is required")
    @Size(min = 5, message = "Phone number must be at least 5 characters")
    @PhoneNumberValid(message = "Phone number is not valid")
    private String phoneNumber;

    @JsonProperty("status")
    private String status;

    @NotBlank(message = "Address is required")
    private String address;

    private String note;

    @JsonProperty("total_money")
    @Min(value = 0, message = "Total money must be >= 0")
    @DecimalMin(value = "0.0", inclusive = false, message = "Total money must be greater than 0")
    private Float totalMoney;

    @JsonProperty("shipping_method")
    @NotNull(message = "Shipping method is required")
    private ShippingMethod shippingMethod;

    @JsonProperty("shipping_address")
    @NotBlank(message = "Shipping address is required")
    private String shippingAddress;

    @JsonProperty("payment_method")
    @NotNull(message = "Payment method is required")
    private PaymentMethod paymentMethod;

    @JsonProperty("shipping_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Shipping date must be today or in the future")
    private LocalDate shippingDate;

    @JsonProperty("coupon_code")
    private String couponCode;
}
