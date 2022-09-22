package com.example.ValidatorPattern.service;

import com.example.ValidatorPattern.model.Language;

import java.util.List;

public interface LanguageService {
    Language create(Language language);
    List<Language> getAll();
    Language getById(int id);
}
