package com.example.ValidatorPattern.service.impl.validators;

import com.example.ValidatorPattern.exceptions.ValidationException;
import com.example.ValidatorPattern.model.Language;
import com.example.ValidatorPattern.service.validators.LanguageValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LanguageValidatorImpl implements LanguageValidatorService {
    @Override
    public boolean validate(Language language) throws ValidationException {
        if (language==null||language.getId()==null||language.getId()==0)
            throw new ValidationException("Language hasn't id");
        return true;
    }
}
