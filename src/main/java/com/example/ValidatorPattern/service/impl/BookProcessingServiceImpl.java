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

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    @Override
    public boolean markAllBooksAsRead() {
        Map<User, List<Book>> cases = userService.getAllAvailableCasesV1();

        cases.forEach((user, books)->{
            markThatUserGetBooksToRead(user, books);
        });
        return true;
    }
    @Transactional
    private boolean markThatUserGetBooksToRead(User user, List<Book> books){
        books.stream()
                .forEach(book -> {
                        ReadBookId readBookId = new ReadBookId(book.getId(), user.getId());
                        ReadBook readBok = new ReadBook(readBookId,false, new Date());
                        System.out.println(readBok);
                        readBookService.save(readBok);
        });
        return true;
    }
}

