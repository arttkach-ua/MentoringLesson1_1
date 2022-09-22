package com.example.ValidatorPattern.controller;

import com.example.ValidatorPattern.dto.BookDto;
import com.example.ValidatorPattern.model.Book;
import com.example.ValidatorPattern.model.Language;
import com.example.ValidatorPattern.service.BookService;
import com.example.ValidatorPattern.service.LanguageService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/language")
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageService languageService;

    @PostMapping("/create")
    public ResponseEntity<Language> create(@RequestBody @NonNull Language language) {
        return ResponseEntity.ok(languageService.create(language));
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Language>> getAll() {
        return ResponseEntity.ok(languageService.getAll());
    }

    @GetMapping("/get")
    public ResponseEntity<Language> getById(@RequestBody int languageId) {
        return ResponseEntity.ok(languageService.getById(languageId));
    }
}
