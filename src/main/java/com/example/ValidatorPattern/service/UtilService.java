package com.example.ValidatorPattern.service;

import com.example.ValidatorPattern.model.Book;
import com.example.ValidatorPattern.model.User;

import java.util.List;

public interface UtilService {
    boolean markThatUserGetBooksToRead(User user, List<Book> books);
}
