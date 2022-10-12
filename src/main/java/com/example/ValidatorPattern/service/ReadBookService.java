package com.example.ValidatorPattern.service;

import com.example.ValidatorPattern.dto.ReadBookDto;
import com.example.ValidatorPattern.model.ReadingRoom;
import com.example.ValidatorPattern.model.readBook.ReadBook;
import com.example.ValidatorPattern.model.readBook.ReadBookId;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public interface ReadBookService {
    ReadBook save(ReadBook readBook);

    boolean readBookInAnyRoom(ReadBookDto readBookDto);

}
