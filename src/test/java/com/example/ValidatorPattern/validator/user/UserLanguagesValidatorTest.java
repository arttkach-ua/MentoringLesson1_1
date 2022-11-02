package com.example.ValidatorPattern.validator.user;

import com.example.ValidatorPattern.exceptions.ValidationException;
import com.example.ValidatorPattern.model.Language;
import com.example.ValidatorPattern.model.User;
import com.example.ValidatorPattern.service.LanguageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserLanguagesValidatorTest {

    @Autowired
    UserLanguagesValidator userLanguagesValidator;

    @MockBean
    LanguageService languageService;

    @Test
    void validatePositiveCase() {
        Language lang = new Language(1, "test1");
        User user = new User(1,"user",20,"e-mail@gmail.com", List.of(lang));
        assertDoesNotThrow(()->userLanguagesValidator.validate(user));
    }

    @Test
    void validateNullLanguageShouldThrowException() {
        User user = new User(1,"user",20,"e-mail@gmail.com",null);
        ValidationException ex = assertThrows(ValidationException.class,()->userLanguagesValidator.validate(user));
        assertEquals("Language is not present in database", ex.getMessage());
        //assertDoesNotThrow(()->userLanguagesValidator.validate(user));
    }

    @Test
    void validateWrongIdLanguageShouldThrowException() {
        Language lang = new Language(-1, "test1");
        User user = new User(1,"user",20,"e-mail@gmail.com",List.of(lang));
        ValidationException ex = assertThrows(ValidationException.class,()->userLanguagesValidator.validate(user));
        assertEquals("Language is not present in database", ex.getMessage());
        //assertDoesNotThrow(()->userLanguagesValidator.validate(user));
    }
}