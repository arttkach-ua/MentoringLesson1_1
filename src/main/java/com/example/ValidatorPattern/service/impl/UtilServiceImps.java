package com.example.ValidatorPattern.service.impl;

import com.example.ValidatorPattern.model.Book;
import com.example.ValidatorPattern.model.User;
import com.example.ValidatorPattern.model.readBook.ReadBook;
import com.example.ValidatorPattern.model.readBook.ReadBookId;
import com.example.ValidatorPattern.service.ReadBookService;
import com.example.ValidatorPattern.service.UtilService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class UtilServiceImps implements UtilService {
    static int casesProcessed;
    @Autowired
    private ReadBookService readBookService;
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
            throw new RuntimeException();
        }
    }
}
