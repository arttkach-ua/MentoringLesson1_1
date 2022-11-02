package com.example.ValidatorPattern.service.impl.validators;

import com.example.ValidatorPattern.exceptions.ValidationException;
import com.example.ValidatorPattern.model.Language;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LanguageValidatorImplTest {

    @Autowired
    LanguageValidatorImpl languageValidator;

    @Test
    void validateLangIsNullShouldThrowException() {
        Language lang = null;
        assertThrows(ValidationException.class,()->languageValidator.validate(lang));
    }

    @Test
    void validateLangIdIsNullShouldThrowException() {
        Language lang = new Language(null, "lang");
        assertThrows(ValidationException.class,()->languageValidator.validate(lang));
    }

    @Test
    void validateIdIsZeroShouldThrowException() {
        Language lang = new Language(0, "lang");
        assertThrows(ValidationException.class,()->languageValidator.validate(lang));
    }

    @Test
    void validatePositiveCaseShouldNotThrowException() {
        Language lang = new Language(1, "lang");
        assertDoesNotThrow(()->languageValidator.validate(lang));
    }
}