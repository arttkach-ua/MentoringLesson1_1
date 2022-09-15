package com.example.ValidatorPattern.service;

import com.example.ValidatorPattern.model.Language;

import javax.xml.bind.ValidationException;

public interface LanguageValidatorService {
    boolean validate(Language language) throws ValidationException;
}
