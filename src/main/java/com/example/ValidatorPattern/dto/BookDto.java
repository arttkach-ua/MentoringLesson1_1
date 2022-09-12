package com.example.ValidatorPattern.dto;

import com.example.ValidatorPattern.model.Book;
import lombok.Data;

@Data
public class BookDto {
    private Integer pages;
    private String author;
    private String name;

    public Book toBook(){
        Book book = new Book();
        book.setAuthor(author);
        book.setPages(pages);
        book.setName(name);
        return book;
    }
}
