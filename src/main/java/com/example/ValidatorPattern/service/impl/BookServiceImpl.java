package com.example.ValidatorPattern.service.impl;

import com.example.ValidatorPattern.dto.BookDto;
import com.example.ValidatorPattern.exceptions.ValidationException;
import com.example.ValidatorPattern.model.Book;
import com.example.ValidatorPattern.model.User;
import com.example.ValidatorPattern.reposithory.BookRepository;
import com.example.ValidatorPattern.reposithory.UserRepository;
import com.example.ValidatorPattern.service.BookService;
import com.example.ValidatorPattern.service.validators.BookValidatorService;

import com.example.ValidatorPattern.util.Mapper;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookValidatorService validatorService;

    private final BookRepository bookRepository;

    private final UserRepository userRepository;

    private final Mapper mapper;

    @Override
    public Book create(BookDto bookDto) {

        Book book = mapper.toBook(bookDto);

        validatorService.validate(book);
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(int id) {
        return bookRepository
                .findById(id)
                .orElseThrow(()->new ValidationException(String.format("Book with id %d not found",id)));
    }

    @Override
    public List<User> findUsersForBook(int bookId) {
        return userRepository.findUsersForBook(bookId);
    }

    @Override
    public void markBoodAsRead(Book book, User user) {
        bookRepository.saveBookAsRead(book.getId(),user.getId());
    }


}
