package com.example.ValidatorPattern.controller;

import com.example.ValidatorPattern.dto.BookDto;
import com.example.ValidatorPattern.model.Book;
import com.example.ValidatorPattern.model.Language;
import com.example.ValidatorPattern.service.BookService;
import com.example.ValidatorPattern.service.LanguageService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/language")
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageService languageService;

    @PostMapping("/create")
    public ResponseEntity<Language> create(@RequestBody @NonNull Language language) {
        return ResponseEntity.ok(languageService.create(language));
    }
}
