package com.example.ValidatorPattern.validator.book;

import com.example.ValidatorPattern.exceptions.ValidationException;
import com.example.ValidatorPattern.model.Book;
import com.example.ValidatorPattern.util.Constant;
import com.example.ValidatorPattern.validator.BookValidator;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookAuthorValidator implements BookValidator {

    @Override
    public void validate(Book book) {
        Optional.ofNullable(book.getAuthor())
                .filter(this::doesAuthorMatchPattern)
                .orElseThrow(this::constructValidationException);
    }

    private boolean doesAuthorMatchPattern(String author) {
        return Constant.NAME_REGEX.matcher(author).matches();
    }

    private ValidationException constructValidationException() {
        return new ValidationException("Author is not valid");
    }

}
