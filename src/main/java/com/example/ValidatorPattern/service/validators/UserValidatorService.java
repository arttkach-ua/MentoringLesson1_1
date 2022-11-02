package com.example.ValidatorPattern.service.validators;

import com.example.ValidatorPattern.model.User;

public interface UserValidatorService {

    void validate(User user);

    void validateAdmin(User user);

    void validateAgeAndName(User user);
}
