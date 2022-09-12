package com.example.ValidatorPattern.service.impl;

import com.example.ValidatorPattern.model.User;
import com.example.ValidatorPattern.service.UserValidatorService;
import com.example.ValidatorPattern.util.UserValidatorName;
import com.example.ValidatorPattern.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

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
        List<User> users = initUsers();

        List<String> userList = users.stream()
                .flatMap(a->a.getLanguages().stream())
                .collect(Collectors.toList());
    }
    private List<User> initUsers(){
        List<String> lang = new ArrayList<>();
        lang.add("English");
        lang.add("French");
        List<String> lang1 = new ArrayList<>();
        lang1.add("English");
        lang1.add("French");
        lang1.add("Spanish");

        User u1 = new User();
        User u2 = new User();
        User u3 = new User();

        u1.setLanguages(lang);
        u2.setLanguages(lang);
        u3.setLanguages(lang1);

        List<User> users = new ArrayList<>();
        users.add(u1);
        users.add(u2);
        users.add(u3);

        return users;
    }
}
