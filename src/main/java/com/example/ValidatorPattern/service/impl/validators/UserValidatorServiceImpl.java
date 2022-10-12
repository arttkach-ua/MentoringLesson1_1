package com.example.ValidatorPattern.service.impl.validators;

import com.example.ValidatorPattern.model.User;
import com.example.ValidatorPattern.service.validators.UserValidatorService;
import com.example.ValidatorPattern.util.UserValidatorName;
import com.example.ValidatorPattern.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserValidatorServiceImpl implements UserValidatorService {

    private final Map<String, UserValidator> validators;
    private final Set <String> validatorsAgeAndName = new HashSet<>();
    private Set<String> adminValidatorNames = new HashSet<>();

    @PostConstruct
    public void init() {
        adminValidatorNames.add(UserValidatorName.AGE_VALIDATOR);

        initAgeAndNameValidator();
    }

    @Override
    public void validate(User user){
        validators.values().forEach(v -> v.validate(user));
    }

    @Override
    public void validateAdmin(User user){
        validators.entrySet().stream()
                .filter(e -> adminValidatorNames.contains(e.getKey()))
                .forEach(e -> e.getValue().validate(user));
    }

    @Override
    public void validateAgeAndName(User user){
        validators.entrySet().stream()
                .filter(e -> validatorsAgeAndName.contains(e.getKey()))
                .forEach(e -> e.getValue().validate(user));

    }


    private void initAgeAndNameValidator(){
        validatorsAgeAndName.add(UserValidatorName.NAME_VALIDATOR);
        validatorsAgeAndName.add(UserValidatorName.AGE_VALIDATOR);
    }

    @Override
    public void deleteAfterWorking(){
//        List<User> users = initUsers();
//
//        List<Language> userList = users.stream()
//                .flatMap(a->a.getLanguages().stream())
//                .collect(Collectors.toList());
    }

}
