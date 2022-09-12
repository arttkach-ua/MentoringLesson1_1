package com.example.ValidatorPattern.validator.book;

import com.example.ValidatorPattern.model.Book;
import com.example.ValidatorPattern.validator.BookValidator;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookPagesValidator implements BookValidator {

    @Override
    public void validate(Book book) {
        Optional.ofNullable(book.getPages())
                .filter(this::arePagesMoreThanZero)
                .orElseThrow(this::constructValidationException);
    }

    private boolean arePagesMoreThanZero(int pages) {
        return pages > 0;
    }

    private IllegalArgumentException constructValidationException() {
        return new IllegalArgumentException("Pages should be more than 0");
    }
}
