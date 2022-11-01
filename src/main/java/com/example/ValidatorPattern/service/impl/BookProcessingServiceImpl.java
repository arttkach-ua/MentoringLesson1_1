package com.example.ValidatorPattern.service.impl;

import com.example.ValidatorPattern.dto.ReadBookDto;
import com.example.ValidatorPattern.model.Book;
import com.example.ValidatorPattern.model.ReadingRoom;
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
    @Autowired
    private ReadingRoomService readingRoomService;
    @Lazy
    private BookProcessingService self;

    @Override
    public ReadBook markBookAsRead(ReadBookId readBookId) {
        bookService.findById(readBookId.getBookId());
        userService.findById(readBookId.getUserId());

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

    @Override
    public boolean readBookInAnyReadingRoom(ReadBookDto readBookDto) {
        User user = userService.findById(readBookDto.getUserId());
        Book book = bookService.findById(readBookDto.getBookId()) ;
        ReadingRoom readingRoom = readingRoomService.findAnyFreeRoom();

        ReadBookId readBookId = new ReadBookId(readBookDto.getBookId(),readBookDto.getUserId());
        ReadBook readBook = new ReadBook(readBookId,true, new Date());
        readBookService.save(readBook);

        readingRoom.setIsAvailable(false);
        readingRoomService.save(readingRoom);

        readingRoomService.freeRoomWithDelay(readingRoom);
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


