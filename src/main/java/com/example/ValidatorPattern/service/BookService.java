package com.example.ValidatorPattern.service;

import com.example.ValidatorPattern.dto.BookDto;
import com.example.ValidatorPattern.model.Book;
import com.example.ValidatorPattern.model.User;

import java.util.List;

public interface BookService {

    Book create(BookDto bookDto);

    List<Book> getAll();

    Book findById(int id);

    List<User>findUsersForBook(int BookId);

    void markBoodAsRead(Book book, User user);
}
