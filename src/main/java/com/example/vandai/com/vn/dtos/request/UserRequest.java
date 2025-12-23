package com.example.vandai.com.vn.dtos.request;

import com.example.vandai.com.vn.validation.passwordCheck.PasswordValid;
import com.example.vandai.com.vn.validation.phoneCheck.PhoneNumberValid;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Data//toString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("phone_number")
    @PhoneNumberValid(message ="Phone number valid")
    private String phoneNumber ;

    @JsonProperty("email")
    private String email = "";

    private String address;

    @NotBlank(message = "Password cannot be blank")
    @PasswordValid(message = "Password is not in correct format")
    private String password;

    @JsonProperty("retype_password")
    public String retypePassword = "";

    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    @JsonProperty("facebook_account_id")
    private String facebookAccountId;

    @JsonProperty("google_account_id")
    private String googleAccountId;

    @NotNull(message = "Role ID is required")
    @JsonProperty("role_id")
    private Long roleId;
}
