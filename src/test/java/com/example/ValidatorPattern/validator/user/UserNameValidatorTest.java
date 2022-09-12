package com.example.ValidatorPattern.validator.user;

import com.example.ValidatorPattern.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = { UserNameValidator.class})
class UserNameValidatorTest {

    @Autowired
    UserNameValidator validator;


    @Test
    void validateUserIsNull() {
        assertThrows(NullPointerException.class, () -> {
            validator.validate(null);
        });
    }
    @Test
    void validateNameIsNull(){
        User user = new User();
        user.setAge(25);
        user.setEmail("sss@gmail.com");
        assertThrows(IllegalArgumentException.class, ()->{validator.validate(user);});
    }

    @Test
    void validateNameLengthIsZero(){
        User user = new User();
        user.setAge(25);
        user.setName("");
        user.setEmail("sss@gmail.com");
        assertThrows(IllegalArgumentException.class, ()->{validator.validate(user);});
    }

    @Test
    void validateOneWordName(){
        User user = new User();
        user.setAge(25);
        user.setName("sss");
        user.setEmail("sss@gmail.com");
        assertDoesNotThrow(()->{validator.validate(user);});
    }

    @Test
    void validateTwoWordsName(){
        User user = new User();
        user.setAge(25);
        user.setName("sss sss");
        user.setEmail("sss@gmail.com");
        assertThrows(IllegalArgumentException.class, ()->{validator.validate(user);});
    }

}