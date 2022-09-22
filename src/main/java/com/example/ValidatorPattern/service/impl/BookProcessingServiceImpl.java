package com.example.ValidatorPattern.service.impl;

import com.example.ValidatorPattern.model.Book;
import com.example.ValidatorPattern.model.User;
import com.example.ValidatorPattern.model.readBook.ReadBook;
import com.example.ValidatorPattern.model.readBook.ReadBookId;
import com.example.ValidatorPattern.reposithory.BookRepository;
import com.example.ValidatorPattern.service.BookProcessingService;
import com.example.ValidatorPattern.service.BookService;
import com.example.ValidatorPattern.service.ReadBookService;
import com.example.ValidatorPattern.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class BookProcessingServiceImpl implements BookProcessingService {
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    @Autowired
    ReadBookService readBookService;

    @Override
    public ReadBook markBookAsRead(ReadBookId readBookId) {
        bookService.findById(readBookId.getBookId());
        userService.findById(readBookId.getUserId());

        //TODO: do procedure to set book as read
        ReadBook readBook = new ReadBook(readBookId,true, new Date());
        return readBookService.save(readBook);
    }
}
