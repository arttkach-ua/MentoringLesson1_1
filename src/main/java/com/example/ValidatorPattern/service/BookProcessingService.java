package com.example.ValidatorPattern.service;

import com.example.ValidatorPattern.model.readBook.ReadBook;
import com.example.ValidatorPattern.model.readBook.ReadBookId;
import org.springframework.stereotype.Service;

public interface BookProcessingService {
    ReadBook markBookAsRead(ReadBookId readBook);
}
