package com.example.ValidatorPattern.service.impl;

import com.example.ValidatorPattern.model.Book;
import com.example.ValidatorPattern.model.User;
import com.example.ValidatorPattern.model.readBook.ReadBook;
import com.example.ValidatorPattern.model.readBook.ReadBookId;
import com.example.ValidatorPattern.service.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class BookProcessingServiceImpl implements BookProcessingService {
    static int casesProcessed;
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    @Autowired
    public ReadBookService readBookService;

    @Autowired
    public UtilService utilService;

    @Lazy
    private BookProcessingService self;
//    @Autowired
//    ApplicationContext applicationContext;

//    @PostConstruct
//    void init() {
//        this.self = applicationContext.getBean(BookProcessingServiceImpl.class);
//    }


    @Override
    public ReadBook markBookAsRead(ReadBookId readBookId) {
        bookService.findById(readBookId.getBookId());
        userService.findById(readBookId.getUserId());

        //TODO: do procedure to set book as read
        ReadBook readBook = new ReadBook(readBookId, true, new Date());
        return readBookService.save(readBook);
    }

    @Override
    public boolean markAllBooksAsRead() {
        Map<User, List<Book>> cases = userService.getAllAvailableCasesV1();

        cases.forEach((user, books) -> self.markThatUserGetBooksToRead(user, books));
        return true;
    }

    @Override
    @Transactional
    @Modifying
    public boolean markThatUserGetBooksToRead(User user, List<Book> books){
        books.stream()
                .forEachOrdered(book -> {
                    ReadBookId readBookId = new ReadBookId(book.getId(), user.getId());
                    ReadBook readBok = new ReadBook(readBookId,false, new Date());
                    throwRandException();
                    System.out.println(String.format("Not thrown? %s", user.getName()));
                    readBookService.save(readBok);
                });
        return true;
    }

    public static void throwRandException() {
        casesProcessed++;
        if (casesProcessed==5){
            System.out.println("thrown exception");
            casesProcessed = 0;
            throw new RuntimeException();
        }
    }


}


