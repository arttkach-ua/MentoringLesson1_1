package com.example.ValidatorPattern.validator.user;

import com.example.ValidatorPattern.exceptions.ValidationException;
import com.example.ValidatorPattern.model.Language;
import com.example.ValidatorPattern.model.User;
import com.example.ValidatorPattern.validator.UserValidator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component()
public class UserLanguagesValidator implements UserValidator {

    @Override
    public void validate(User user) {
        Optional.ofNullable(user.getLanguages())
                .filter(this::idIsPresent)
                .orElseThrow(this::constructValidationException);
    }

    private boolean idIsPresent(List<Language> languages) {
        for (Language language:languages) {
            if (language.getId()==null||language.getId() < 1)
                return false;
        }
        return true;
    }

    private ValidationException constructValidationException() {
        return new ValidationException("Language is not present in database");
    }
}
