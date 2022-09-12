package com.example.ValidatorPattern.validator.user;

import com.example.ValidatorPattern.exceptions.ValidationException;
import com.example.ValidatorPattern.model.User;
import com.example.ValidatorPattern.util.UserValidatorName;
import com.example.ValidatorPattern.validator.UserValidator;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component(UserValidatorName.AGE_VALIDATOR)
public class UserAgeValidator implements UserValidator {

    @Override
    public void validate(User user) {
        Optional.ofNullable(user.getAge())
                .filter(this::isAgeMoreThanZero)
                .orElseThrow(this::constructValidationException);
    }

    private boolean isAgeMoreThanZero(int age) {
        return age > 0;
    }

    private ValidationException constructValidationException() {
        return new ValidationException("Age should be more than 0");
    }

}
