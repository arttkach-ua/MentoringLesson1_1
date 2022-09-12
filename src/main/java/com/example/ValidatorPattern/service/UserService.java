package com.example.ValidatorPattern.service;

import com.example.ValidatorPattern.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    User create(User user);

    User createAdmin(User user);

    User validateName(User user);

    User validateAgeAndName(User user);

    void deleteAfterWorking() throws Exception;

    Map<String, List<User>> collectToMap();
}
