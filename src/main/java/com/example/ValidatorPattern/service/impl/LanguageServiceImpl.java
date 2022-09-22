package com.example.ValidatorPattern.service.impl;

import com.example.ValidatorPattern.exceptions.ValidationException;
import com.example.ValidatorPattern.model.Language;
import com.example.ValidatorPattern.reposithory.LanguageRepository;
import com.example.ValidatorPattern.service.LanguageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    LanguageRepository languageRepository;

    @Override
    public Language create(Language language) {
        return languageRepository.save(language);
    }

    @Override
    public List<Language> getAll() {
        return languageRepository.findAll();
    }

    @Override
    public Language getById(int id) {
        return languageRepository.findById(id)
                .orElseThrow(()-> new ValidationException("No language found with id " + id));
    }

}
