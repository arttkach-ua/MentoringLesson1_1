package com.example.ValidatorPattern.service.impl;

import com.example.ValidatorPattern.model.Language;
import com.example.ValidatorPattern.reposithory.LanguageRepository;
import com.example.ValidatorPattern.service.LanguageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    LanguageRepository languageRepository;

    @Override
    public Language create(Language language) {
        return languageRepository.save(language);
    }
}
