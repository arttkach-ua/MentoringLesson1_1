package com.example.ValidatorPattern.service.impl;

import com.example.ValidatorPattern.dto.BookDto;
import com.example.ValidatorPattern.exceptions.ValidationException;
import com.example.ValidatorPattern.model.Book;
import com.example.ValidatorPattern.model.Language;
import com.example.ValidatorPattern.model.User;
import com.example.ValidatorPattern.reposithory.BookRepository;
import com.example.ValidatorPattern.reposithory.UserRepository;
import com.example.ValidatorPattern.service.BookService;
import com.example.ValidatorPattern.service.LanguageService;
import com.example.ValidatorPattern.util.Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class BookServiceImplTest {
    @MockBean
    private BookRepository bookRepository;

    @MockBean
    UserRepository userRepository;

    @MockBean
    private LanguageService languageService;
    @MockBean
    Mapper mapper;

    @Autowired
    BookService bookService;

    @Test
    void createBookPositiveCase() {
        //1. Given
        //2. When
        //3. Then

        //Given
        Language testLanguage = new Language(1, "TestLanguage");
        BookDto dto = new BookDto(200, "author", "book1", 1);
        Book expectedBook = Book.builder()
                .author("author")
                .pages(200)
                .name("book1")
                .language(testLanguage)
                .build();

        when(mapper.toBook(dto))
                .thenReturn(expectedBook);

        doReturn(expectedBook)
                .when(bookRepository)
                .save(any(Book.class));
        //When
        Book actualBook = bookService.create(dto);
        //Then
        assertEquals(expectedBook, actualBook);
        verify(bookRepository,times(1)).save(any(Book.class));
    }

    @Test
    void createBookNullAuthorShouldThrowException(){
        Language testLanguage = new Language(1, "TestLanguage");
        BookDto dto = new BookDto(200, null, "test1", 1);
        Book expectedBook = Book.builder()
                .pages(200)
                .name("book1")
                .language(testLanguage)
                .build();

        when(mapper.toBook(dto))
                .thenReturn(expectedBook);
        Book book = mapper.toBook(dto);

        when(bookRepository.save(book))
                .thenReturn(expectedBook);
        assertThrows(ValidationException.class, ()->bookService.create(dto));
    }

    @Test
    void createBookEmptyAuthorShouldThrowException(){
        Language testLanguage = new Language(1, "TestLanguage");
        BookDto dto = new BookDto(200, "", "test1", 1);
        Book expectedBook = Book.builder()
                .author("")
                .pages(200)
                .name("book1")
                .language(testLanguage)
                .build();

        when(mapper.toBook(dto))
                .thenReturn(expectedBook);
        Book book = mapper.toBook(dto);

        when(bookRepository.save(book))
                .thenReturn(expectedBook);
        assertThrows(ValidationException.class, ()->bookService.create(dto));
    }

    @Test
    void createBookZeroPagesShouldThrowException(){
        Language testLanguage = new Language(1, "TestLanguage");
        BookDto dto = new BookDto(0, "author", "test1", 1);
        Book expectedBook = Book.builder()
                .author("author")
                .pages(0)
                .name("book1")
                .language(testLanguage)
                .build();

        when(mapper.toBook(dto))
                .thenReturn(expectedBook);

        Book book = mapper.toBook(dto);
        when(bookRepository.save(book))
                .thenReturn(expectedBook);
        assertThrows(IllegalArgumentException.class, ()->bookService.create(dto));
    }

    @Test
    void getAll(){
        List<Book> mockedList = new ArrayList<>();
        mockedList.add(new Book(1,"book1",200,"author", null));
        mockedList.add(new Book(2,"book2",200,"author", null));

        when(bookRepository.findAll())
                .thenReturn(mockedList);
        List<Book> books = bookService.getAll();
        assertEquals(mockedList, books);
    }
    //TODO verify
    @Test
    void findByIdPositiveCase(){
        int id = 999;
        Language testLanguage = new Language(1, "TestLanguage");
        Book expectedBook = Book.builder()
                .id(id)
                .author("author")
                .pages(200)
                .name("book1")
                .language(testLanguage)
                .build();

        when(bookRepository.findById(id)).thenReturn(Optional.of(expectedBook));
        assertEquals(expectedBook, bookService.findById(id));
    }

    @Test
    void findByIdNegativeIdShouldThrowException(){
        int id = -999;

        when(bookRepository.findById(id)).thenReturn(Optional.ofNullable(null));
        assertThrows(ValidationException.class,()->bookService.findById(id));
    }

    @Test
    void findUsersForBookCheckRepositoryCallTimes() {
        //Given
        Integer bookId = 1;
        //When
        when(userRepository.findUsersForBook(anyInt()))
                .thenReturn(new ArrayList<>());
        //Then
        bookService.findUsersForBook(bookId);
        verify(userRepository,times(1)).findUsersForBook(anyInt());
    }

    @Test
    void markBookAsReadCheckRepositoryCallTimes() {
        //Given
        Book book = new Book(1,"book",200,"author",null);
        User user = new User(1,"user",20,"e-amil.gmail.com",null);
        //When
        doReturn(true).when(bookRepository).saveBookAsRead(anyInt(),anyInt());
        //Then
        bookService.markBoodAsRead(book,user);
        verify(bookRepository,times(1)).saveBookAsRead(anyInt(),anyInt());
    }

}