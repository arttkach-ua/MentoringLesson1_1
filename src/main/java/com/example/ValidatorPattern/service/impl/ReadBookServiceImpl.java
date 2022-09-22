package com.example.ValidatorPattern.service.impl;

import com.example.ValidatorPattern.model.readBook.ReadBook;
import com.example.ValidatorPattern.reposithory.ReadBookRepository;
import com.example.ValidatorPattern.service.ReadBookService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@AllArgsConstructor
@Service
@Component
public class ReadBookServiceImpl implements ReadBookService {
@Autowired
    private ReadBookRepository readBookRepository;

    @Override
    public ReadBook save(ReadBook readBook) {
        return readBookRepository.save(readBook);
    }
}
