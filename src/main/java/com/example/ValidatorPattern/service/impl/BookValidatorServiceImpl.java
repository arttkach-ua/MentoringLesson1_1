package com.example.ValidatorPattern.service.impl;

import com.example.ValidatorPattern.model.Book;
import com.example.ValidatorPattern.service.BookValidatorService;
import com.example.ValidatorPattern.validator.BookValidator;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookValidatorServiceImpl implements BookValidatorService {

    private final List<BookValidator> validators;

    @Override
    public void validate(Book book) {
        validators.forEach(v -> v.validate(book));
    }
}
