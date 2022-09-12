package com.example.ValidatorPattern.service;

import com.example.ValidatorPattern.dto.BookDto;
import com.example.ValidatorPattern.model.Book;

public interface BookService {

    Book create(BookDto bookDto);

}
