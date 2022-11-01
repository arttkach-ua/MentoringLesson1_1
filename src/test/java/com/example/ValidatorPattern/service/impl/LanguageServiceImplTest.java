package com.example.ValidatorPattern.service.impl;

import com.example.ValidatorPattern.exceptions.ValidationException;
import com.example.ValidatorPattern.model.Language;
import com.example.ValidatorPattern.reposithory.LanguageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class LanguageServiceImplTest {

    @Autowired
    LanguageServiceImpl languageService;

    @MockBean
    LanguageRepository languageRepository;

    @Test
    void createRepositoryCallCheck() {
        Language testLanguage = new Language(1, "TestLanguage");
        when(languageRepository.save(any(Language.class)))
                .thenReturn(testLanguage);
        languageService.create(testLanguage);
        verify(languageRepository, times(1)).save(testLanguage);
    }

    @Test
    void getAll() {
        Language lang1 = new Language(1, "TestLanguage1");
        Language lang2 = new Language(2, "TestLanguage2");
        List<Language> languages = List.of(lang1, lang2);
        when(languageRepository.findAll())
                .thenReturn(languages);
        List<Language> resultLanguages = languageService.getAll();
        assertEquals(languages, resultLanguages);
    }

    @Test
    void getByIdCheckWhenEntityExists() {
        Language lang1 = new Language(1, "TestLanguage1");
        when(languageRepository.findById(anyInt()))
                .thenReturn(Optional.of(lang1));
        Language resultLanguage = languageService.getById(1);
        assertEquals(lang1, resultLanguage);
    }

    @Test
    void getByIdCheckWhenEntityNotExists() {
        Language lang1 = new Language(1, "TestLanguage1");
        when(languageRepository.findById(anyInt()))
                .thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(ValidationException.class,()->languageService.getById(1));
        assertEquals("No language found with id 1", ex.getMessage());
    }
}