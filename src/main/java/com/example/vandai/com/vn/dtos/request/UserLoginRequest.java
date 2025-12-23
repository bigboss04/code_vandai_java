package com.example.vandai.com.vn.dtos.request;


import com.example.vandai.com.vn.validation.passwordCheck.PasswordValid;
import com.example.vandai.com.vn.validation.phoneCheck.PhoneNumberValid;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data//toString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequest {
    @JsonProperty("phone_number")
    @NotBlank(message = "Phone number cannot be blank")
    @PhoneNumberValid(message = "Phone number is not valid")
    private String phoneNumber;

    @JsonProperty("password")
    @NotBlank(message = "Password cannot be blank")
    @PasswordValid(message = "Password is not in correct format")
    private String password;
}
