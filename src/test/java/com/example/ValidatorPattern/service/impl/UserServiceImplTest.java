package com.example.ValidatorPattern.service.impl;

import com.example.ValidatorPattern.exceptions.ValidationException;
import com.example.ValidatorPattern.model.Language;
import com.example.ValidatorPattern.model.User;
import com.example.ValidatorPattern.reposithory.LanguageRepository;
import com.example.ValidatorPattern.reposithory.UserRepository;
import com.example.ValidatorPattern.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserServiceImpl userService;

    @MockBean
    UserRepository userRepository;

    @MockBean
    LanguageRepository languageRepository;

    @Test
    void createShouldWorkNormally() {
        Language testLanguage = new Language(1, "TestLanguage");
        User user = new User(1,"user",20,"e-amil@gmail.com", List.of(testLanguage));
        when(userRepository.save(any(User.class)))
                .thenReturn(user);
        when(languageRepository.findById(anyInt()))
                .thenReturn(Optional.of(testLanguage));
        User resultUser = userService.create(user);
        assertEquals(user, resultUser);
    }

    @Test
    void validateAgeAndNameCorrectUser() {
        Language testLanguage = new Language(1, "TestLanguage");
        User user = new User(1,"user",20,"e-amil@gmail.com", List.of(testLanguage));
        userService.validateAgeAndName(user);
    }

    @Test
    void validateAgeAndNameWrongAge() {
        Language testLanguage = new Language(1, "TestLanguage");
        User user = new User(1,"user",-2,"e-amil@gmail.com", List.of(testLanguage));
        RuntimeException ex = assertThrows(ValidationException.class, ()->userService.validateAgeAndName(user));
        assertEquals("Age should be more than 0", ex.getMessage());
    }

    @Test
    void validateAgeAndNameWrongName() {
        Language testLanguage = new Language(1, "TestLanguage");
        User user = new User(1,"",20,"e-amil@gmail.com", List.of(testLanguage));
        RuntimeException ex = assertThrows(ValidationException.class, ()->userService.validateAgeAndName(user));
        assertEquals("Name is not valid", ex.getMessage());
    }
}