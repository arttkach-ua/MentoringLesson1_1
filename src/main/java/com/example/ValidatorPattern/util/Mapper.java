package com.example.ValidatorPattern.util;

import com.example.ValidatorPattern.dto.BookDto;
import com.example.ValidatorPattern.model.Book;
import com.example.ValidatorPattern.service.LanguageService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@RequiredArgsConstructor
@Component
public class Mapper {
    @Autowired
    private LanguageService languageService;

    public Book toBook(BookDto dto){
        Book book = new Book();
        book.setAuthor(dto.getAuthor());
        book.setPages(dto.getPages());
        book.setName(dto.getName());
        book.setLanguage(languageService.getById(dto.getLanguageId()));
        return book;
    }
}
