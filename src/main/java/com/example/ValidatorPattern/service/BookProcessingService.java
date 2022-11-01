package com.example.ValidatorPattern.service;

import com.example.ValidatorPattern.dto.ReadBookDto;
import com.example.ValidatorPattern.model.Book;
import com.example.ValidatorPattern.model.ReadingRoom;
import com.example.ValidatorPattern.model.User;
import com.example.ValidatorPattern.model.readBook.ReadBook;
import com.example.ValidatorPattern.model.readBook.ReadBookId;

import java.util.List;

public interface BookProcessingService {
    ReadBook markBookAsRead(ReadBookId readBook);

    boolean markAllBooksAsRead();

    boolean markThatUserGetBooksToRead(User user, List<Book> books);

    boolean readBookInAnyReadingRoom(ReadBookDto readBookDto);
}
