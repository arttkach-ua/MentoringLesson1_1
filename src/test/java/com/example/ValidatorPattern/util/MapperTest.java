package com.example.ValidatorPattern.util;

import com.example.ValidatorPattern.dto.BookDto;
import com.example.ValidatorPattern.model.Book;
import com.example.ValidatorPattern.model.Language;
import com.example.ValidatorPattern.service.LanguageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class MapperTest {

    @MockBean
    LanguageService languageService;

    @Autowired
    Mapper mapper;

    @Test
    void toBookPositiveTest(){
        //Given
        Language testLanguage = new Language(1, "ua");
        BookDto dto = BookDto.builder()
                .author("author")
                .name("book1")
                .pages(200)
                .languageId(1)
                .build();
        Book expectedBook = Book.builder()
                .author("author")
                .name("book1")
                .language(testLanguage)
                .pages(200)
                .build();
        doReturn(testLanguage)
                .when(languageService)
                .getById(any(Integer.class));
        //When
        Book actualBook = mapper.toBook(dto);

        //Then
        assertEquals(expectedBook, actualBook);


    }

}