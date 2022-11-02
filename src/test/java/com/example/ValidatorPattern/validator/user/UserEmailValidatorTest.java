package com.example.ValidatorPattern.validator.user;

import com.example.ValidatorPattern.exceptions.ValidationException;
import com.example.ValidatorPattern.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserEmailValidatorTest {

    @Autowired
    UserEmailValidator userEmailValidator;

    @Test
    void validateValidUserShouldPass() {
        User user = new User(1,"user",20,"e-mail@gmail.com",null);
        assertDoesNotThrow(()->userEmailValidator.validate(user));
    }

    @Test
    void validateInvalidUserShouldThrowException() {
        User user = new User(1,"user",20,"e-mail.gmail.com",null);
        Exception ex = assertThrows(ValidationException.class, ()->userEmailValidator.validate(user));
        assertEquals("Email is not valid", ex.getMessage());
    }
}