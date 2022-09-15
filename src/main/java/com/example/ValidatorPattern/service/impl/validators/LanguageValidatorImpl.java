package com.example.ValidatorPattern.service.impl.validators;

import com.example.ValidatorPattern.model.Language;
import com.example.ValidatorPattern.service.LanguageValidatorService;

import javax.xml.bind.ValidationException;

public class LanguageValidatorImpl implements LanguageValidatorService {
    @Override
    public boolean validate(Language language) throws ValidationException {
        if (language.getId()==null||language.getId()==0)
            throw new ValidationException("Language hasn't id");
        return true;
    }
}
