package com.example.ValidatorPattern.service.impl;

import com.example.ValidatorPattern.dto.BookDto;
import com.example.ValidatorPattern.model.Book;
import com.example.ValidatorPattern.reposithory.BookRepository;
import com.example.ValidatorPattern.service.BookService;
import com.example.ValidatorPattern.service.BookValidatorService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookValidatorService validatorService;

    private final BookRepository bookRepository;

    @Override
    public Book create(BookDto bookDto) {
        Book book = bookDto.toBook() ;

        validatorService.validate(book);
        return bookRepository.save(book);
    }

}
