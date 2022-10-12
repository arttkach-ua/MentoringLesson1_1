package com.example.ValidatorPattern.service.impl;

import com.example.ValidatorPattern.exceptions.ValidationException;
import com.example.ValidatorPattern.model.Book;
import com.example.ValidatorPattern.model.User;
import com.example.ValidatorPattern.reposithory.BookRepository;
import com.example.ValidatorPattern.reposithory.UserRepository;
import com.example.ValidatorPattern.service.LanguageService;
import com.example.ValidatorPattern.service.ReadBookService;
import com.example.ValidatorPattern.service.UserService;
import com.example.ValidatorPattern.service.validators.UserValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserValidatorService validatorService;

    private final UserRepository userRepository;

    private final LanguageService languageService;

    private final BookRepository bookRepository;

    private final ReadBookService readBookService;

    @Override
    public User create(User user) {
        validatorService.validate(user);
        return userRepository.save(user);
    }

    private void saveUsersLanguages(User user) {
        for (int i = 0; i < user.getLanguages().size(); i++) {
            user.getLanguages().set(i, languageService.create(user.getLanguages().get(i)));
        }
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
                   old.stream().forEach(res::add);
                   latest.stream().forEach(res::add);
                   return res;
               }));
//        return prepareUsersList().stream()
//                .collect(Collectors.groupingBy(User::getName,mapping(user->user,toList())));
    }

    @Override
    public List<Book> findBooksToRead(int userId) {
        return bookRepository.findUnleadedBooks(userId);
    }

    @Override
    public User findById(int id) {
        return userRepository
                .findById(id)
                .orElseThrow(()->new ValidationException(String.format("User with id %d not found",id)));
    }

    @Override
    public Map<User, List<Book>> getAllAvailableCasesV1() {
        List<User> users = userRepository.findAll();
        Map<User, List<Book>> result= new HashMap<>();
        users.stream()
                .forEach(user -> {
                    result.put(user,bookRepository.findUnleadedBooks(user.getId()));
                });
        return result;
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
