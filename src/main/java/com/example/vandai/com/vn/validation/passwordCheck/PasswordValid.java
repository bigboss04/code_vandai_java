package com.example.vandai.com.vn.validation.passwordCheck;

import jakarta.validation.Payload;

public @interface PasswordValid {
    String message() default "Password invalid";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
