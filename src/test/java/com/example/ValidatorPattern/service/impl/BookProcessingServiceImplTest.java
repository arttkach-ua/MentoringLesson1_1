package com.example.ValidatorPattern.service.impl;

import com.example.ValidatorPattern.exceptions.ValidationException;
import com.example.ValidatorPattern.model.Book;
import com.example.ValidatorPattern.model.Language;
import com.example.ValidatorPattern.model.User;
import com.example.ValidatorPattern.model.readBook.ReadBook;
import com.example.ValidatorPattern.model.readBook.ReadBookId;
import com.example.ValidatorPattern.service.BookProcessingService;
import com.example.ValidatorPattern.service.BookService;
import com.example.ValidatorPattern.service.ReadBookService;
import com.example.ValidatorPattern.service.UserService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookProcessingServiceImplTest {
    @MockBean
    BookService bookService;
    @MockBean
    UserService userService;

//    @Mock
//    BookProcessingServiceImpl self;
    @Autowired
    BookProcessingServiceImpl bookProcessingService;

    @MockBean
    ReadBookService readBookService;

    @Before("")
    public void before(){

//        when(bookProcessingService.markAllBooksAsRead()).thenReturn(true);
//        MockitoAnnotations.initMocks(this);
    }
    @Test
    void markBookAsReadShouldRunNormally(){
        //Given
        ReadBookId readBookId = new ReadBookId(1,1);
        ReadBook readBook = new ReadBook(readBookId, true, new Date());
        User expectedUser = getExpectedUser();
        Book expectedBook = getExpectedBook();
        when(bookService.findById(anyInt()))
                .thenReturn(expectedBook);
        when(userService.findById(anyInt()))
                .thenReturn(expectedUser);

        when(readBookService.save(readBook))
                .thenReturn(readBook);
        //When
        bookProcessingService.markBookAsRead(readBookId);
        //Then
        verify(readBookService,times(1)).save(any(ReadBook.class));
    }

    @Test
    void markBookAsReadShouldBookNotFound(){
        //Given
        ReadBookId readBookId = new ReadBookId(1,1);
        ReadBook readBook = new ReadBook(readBookId, true, new Date());
        User expectedUser = getExpectedUser();
        when(bookService.findById(anyInt()))
                .thenThrow(new ValidationException(String.format("Book with id %d not found",1)));
        when(userService.findById(anyInt()))
                .thenReturn(expectedUser);

        when(readBookService.save(readBook))
                .thenReturn(readBook);
        //Then
        RuntimeException ex = assertThrows(ValidationException.class, ()->bookProcessingService.markBookAsRead(readBookId));
        assertEquals("Book with id 1 not found", ex.getMessage());
    }

    @Test
    void markBookAsReadShouldUserNotFound(){
        //Given
        ReadBookId readBookId = new ReadBookId(1,1);
        ReadBook readBook = new ReadBook(readBookId, true, new Date());
        Book expectedBook = getExpectedBook();
        when(bookService.findById(anyInt()))
                .thenReturn(expectedBook);
        when(userService.findById(anyInt()))
                .thenThrow(new ValidationException(String.format("User with id %d not found",1)));

        when(readBookService.save(readBook))
                .thenReturn(readBook);
        //Then
        RuntimeException ex = assertThrows(ValidationException.class, ()->bookProcessingService.markBookAsRead(readBookId));
        assertEquals("User with id 1 not found", ex.getMessage());

    }

    private Book getExpectedBook(){
        Language testLanguage = new Language(1, "TestLanguage");
        Book expectedBook = Book.builder()
                .author("author")
                .pages(200)
                .name("book1")
                .language(testLanguage)
                .build();
        return expectedBook;
    }
    private User getExpectedUser(){
        User user = new User(1,"user",20,"e-amil.gmail.com",null);
        return user;
    }
}
