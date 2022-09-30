package com.example.ValidatorPattern.controller;

import com.example.ValidatorPattern.dto.BookDto;
import com.example.ValidatorPattern.model.Book;
import com.example.ValidatorPattern.model.User;
import com.example.ValidatorPattern.model.readBook.ReadBook;
import com.example.ValidatorPattern.model.readBook.ReadBookId;
import com.example.ValidatorPattern.service.BookProcessingService;
import com.example.ValidatorPattern.service.BookService;

import com.example.ValidatorPattern.service.UserService;
import com.sun.istack.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private final UserService userService;

    private final BookProcessingService bookProcessingService;

    @PostMapping("/create")
    public ResponseEntity<Book> create(@RequestBody @NonNull BookDto book) {
        return ResponseEntity.ok(bookService.create(book));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Book>> getAll(){
        return ResponseEntity.ok(bookService.getAll());
    }

    @PostMapping("/markBookAsRead")
    public ResponseEntity<ReadBook> markBookAsRead(@RequestBody @NonNull ReadBookId readBookId){
        return ResponseEntity.ok(bookProcessingService.markBookAsRead(readBookId));
    }

    @GetMapping("/findUsersForBook")
    public ResponseEntity<List<User>> findUsersForBook(@RequestBody @NotNull Integer bookId){
        return ResponseEntity.ok(bookService.findUsersForBook(bookId));
    }

    @PostMapping("/markAllBooksAsRead")
    public ResponseEntity markAllBooksAsRead(){
        return ResponseEntity.ok(userService.markAllBooksAsRead());
    }

}
