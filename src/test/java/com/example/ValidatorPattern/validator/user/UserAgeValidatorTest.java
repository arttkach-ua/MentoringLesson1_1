package com.example.ValidatorPattern.validator.user;

import com.example.ValidatorPattern.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { UserAgeValidator.class})
//@EnableConfigurationProperties
class UserAgeValidatorTest {
    @Autowired
    private UserAgeValidator validator;

    @Test
    void validateUserIsNull() {
        User user = new User();
        user.setAge(-99);
        user.setName("sss sss");
        user.setEmail("sss@gmail.com");
        assertThrows(NullPointerException.class, ()->{validator.validate(null);});
    }

    @Test
    void validateUserMinusAge(){
        User user = new User();
        user.setAge(-99);
        user.setName("sss sss");
        user.setEmail("sss@gmail.com");
        assertThrows(IllegalArgumentException.class, ()->{validator.validate(user);});
    }
    @Test
    void validateUserZeroAge(){
        User user = new User();
        user.setAge(0);
        user.setName("sss sss");
        user.setEmail("sss@gmail.com");
        assertThrows(IllegalArgumentException.class, ()->{validator.validate(user);});
    }

    @Test
    void validateUserPositiveAge(){
        User user = new User();
        user.setAge(25);
        user.setName("sss sss");
        user.setEmail("sss@gmail.com");
        assertDoesNotThrow(()->{validator.validate(user);});
    }
}