package com.example.ValidatorPattern.service;

import com.example.ValidatorPattern.model.Book;
import com.example.ValidatorPattern.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    User create(User user);

    User createAdmin(User user);

    User validateName(User user);

    User validateAgeAndName(User user);

    Map<String, List<User>> collectToMap();

    List<Book> findBooksToRead(int userId);

    User findById(int id);

    Map<User, List<Book>> getAllAvailableCasesV1();

}
