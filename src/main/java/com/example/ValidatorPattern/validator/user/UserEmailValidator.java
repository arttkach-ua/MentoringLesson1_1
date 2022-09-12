package com.example.ValidatorPattern.validator.user;

import com.example.ValidatorPattern.model.User;
import com.example.ValidatorPattern.util.Constant;
import com.example.ValidatorPattern.validator.UserValidator;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserEmailValidator implements UserValidator {

    @Override
    public void validate(User user) {
        Optional.ofNullable(user.getEmail())
                .filter(this::doesEmailMatchPattern)
                .orElseThrow(this::constructValidationException);
    }

    private boolean doesEmailMatchPattern(String email) {
        return Constant.EMAIL_REGEX.matcher(email).matches();
    }

    private IllegalArgumentException constructValidationException() {
        return new IllegalArgumentException("Email is not valid");
    }

}
