package com.example.ValidatorPattern.exceptionHandlers;

import com.example.ValidatorPattern.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
//   @ExceptionHandler(NullPointerException.class)
//   @ResponseStatus(value = HttpStatus.NOT_FOUND)
//    public ResponseEntity processNullPointerException(NullPointerException ex){
//       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getErrorMessage(ex));
//    }

    @ExceptionHandler(value = {ValidationException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity processIllegalArgumentException(ValidationException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getErrorMessage(ex));
    }

    private Map<String, String> getErrorMessage(RuntimeException ex){
        Map<String, String> errors = new HashMap<>();
        errors.put("description","Validation error");
        errors.put("error message",ex.getMessage());
        errors.put("exception type", ex.getClass().getName());
        return errors;
    }
}
