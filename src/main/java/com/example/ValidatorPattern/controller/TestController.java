package com.example.ValidatorPattern.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class TestController {

    @GetMapping
    public String helloGet()
    {
        return "Hello User";
    }

    @PostMapping
    public String helloPost()
    {
        return "Hello User Post";
    }
}
