package com.example.ValidatorPattern.service.impl;

import com.example.ValidatorPattern.model.Language;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class LangH2Test {
    @Autowired
    LanguageServiceImpl languageService;

    @Test
    void saveAndReadTest(){
        Language lang = new Language(1, "lang1");
        languageService.create(lang);

        Language resultLanguage = languageService.getById(1);
        assertEquals("lang1",resultLanguage.getName());
    }
}
