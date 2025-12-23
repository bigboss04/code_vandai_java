package com.example.vandai.com.vn.validation.phoneCheck;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class phoneNumberValidator implements ConstraintValidator<PhoneNumberValid, String> {

  private static final String PHONE_NUMBER_PATTERN = "^(\\+84|0)(3[2-9]|5[6|8|9]|7[0|6-9]|8[1-5]|9[0-4|6-9])[0-9]{7}$";

    @Override
    public void initialize(PhoneNumberValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
        if(phoneNumber == null || phoneNumber.trim().isEmpty()){
            return false;
        }
        return phoneNumber.matches(PHONE_NUMBER_PATTERN);
    }
}
