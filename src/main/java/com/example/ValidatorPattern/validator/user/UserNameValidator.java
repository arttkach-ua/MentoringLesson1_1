package com.example.ValidatorPattern.validator.user;

import com.example.ValidatorPattern.exceptions.ValidationException;
import com.example.ValidatorPattern.model.User;
import com.example.ValidatorPattern.util.Constant;
import com.example.ValidatorPattern.util.UserValidatorName;
import com.example.ValidatorPattern.validator.UserValidator;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component(UserValidatorName.NAME_VALIDATOR)
public class UserNameValidator implements UserValidator {

    @Override
    public void validate(User user) throws ValidationException {
        Optional.ofNullable(user.getName())
                .filter(a->a.length()>0)
                .filter(this::doesNameMatchPattern)
                .orElseThrow(this::constructValidationException);
    }

    private boolean doesNameMatchPattern(String name) {
        return Constant.NAME_REGEX.matcher(name).matches();
    }

    private ValidationException constructValidationException() {
        return new ValidationException("Name is not valid");
    }

}
