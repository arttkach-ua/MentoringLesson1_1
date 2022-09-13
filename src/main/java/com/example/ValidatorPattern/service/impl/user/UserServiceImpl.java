package com.example.ValidatorPattern.service.impl.user;

import com.example.ValidatorPattern.model.Language;
import com.example.ValidatorPattern.model.User;
import com.example.ValidatorPattern.reposithory.UserRepository;
import com.example.ValidatorPattern.service.LanguageService;
import com.example.ValidatorPattern.service.UserService;
import com.example.ValidatorPattern.service.UserValidatorService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserValidatorService validatorService;

    private final UserRepository userRepository;

    private final LanguageService languageService;

    @Override
    public User create(User user) {
        //validatorService.validate(user);
        for (Language lang : user.getLanguages()) {
            if (lang.getId() == null || lang.getId() == 0) {
                lang = languageService.create(lang);
            }
        }
        return userRepository.save(user);
    }

    @Override
    public User createAdmin(User user){
        validatorService.validateAdmin(user);

        return user;
    }

    @Override
    public User validateName(User user){
        validatorService.validate(user);
        return user;
    }

    @Override
    public User validateAgeAndName(User user){
        //throw new SomeException("Some exception was thrown");
        validatorService.validateAgeAndName(user);
        return user;
    }

    @Override
    public void deleteAfterWorking() {
        validatorService.deleteAfterWorking();
    }

    @Override
    public Map<String, List<User>> collectToMap() {
        return prepareUsersList().stream()
               .collect(Collectors.toMap(User::getName, Arrays::asList,(old,latest)->{
                   List<User> res = new ArrayList<>();
                   old.stream().forEach(a->res.add(a));
                   latest.stream().forEach(a->res.add(a));
                   return res;
               }));
//        return prepareUsersList().stream()
//                .collect(Collectors.groupingBy(User::getName,mapping(user->user,toList())));
    }

    private List<User> prepareUsersList(){
        List<User> users = new ArrayList<>();
        users.add(new User(0,"Bob",25,"bob@gmail.com",null));
        users.add(new User(0,"Stefan",25,"stefan@mail.com",null));
        users.add(new User(0,"Stefan",25,"stefan1@mail.com",null));
        users.add(new User(0,"Martin",25,"Martin@mail.com",null));
        users.add(new User(0,"Martin",25,"Martin2@mail.com",null));
        users.add(new User(0,"Martin",25,"Martin3@mail.com",null));
        return users;
    }

}
