package com.example.ValidatorPattern.service.impl;

import com.example.ValidatorPattern.model.readBook.ReadBook;
import com.example.ValidatorPattern.model.readBook.ReadBookId;
import com.example.ValidatorPattern.reposithory.ReadBookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ReadBookServiceImplTest {

    @MockBean
    ReadBookRepository readBookRepository;

    @Autowired
    ReadBookServiceImpl readBookService;
    @Test
    void saveShouldSaveNormally(){
        ReadBookId id = new ReadBookId(1,1);
        ReadBook readBook = new ReadBook(id,true,new Date());
        when(readBookRepository.save(any(ReadBook.class)))
                .thenReturn(readBook);

        readBookService.save(readBook);
        verify(readBookRepository, times(1)).save(readBook);
    }

}