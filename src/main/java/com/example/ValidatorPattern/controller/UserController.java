package com.example.ValidatorPattern.controller;

import com.example.ValidatorPattern.model.User;
import com.example.ValidatorPattern.service.UserService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
//@Service
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody @NonNull User user) {
        return ResponseEntity.ok(userService.create(user));
    }

    @PostMapping("/admin")
    public ResponseEntity<User> createAdmin(@RequestBody @NonNull User user) {
        return ResponseEntity.ok(userService.createAdmin(user));
    }


    @PostMapping("/validateAgeAndName")
    public ResponseEntity<User> validateAgeAndName(@RequestBody @NonNull User user) {
        //userService.deleteAfterWorking();

        return ResponseEntity.ok(userService.validateAgeAndName(user));
    }

    @PostMapping("/collect")
    public ResponseEntity<Map<String, List<User>>> collect(){
        return ResponseEntity.ok(userService.collectToMap());
    }
}
