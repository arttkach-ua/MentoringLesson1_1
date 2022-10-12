package com.example.ValidatorPattern.service.impl.validators;

import com.example.ValidatorPattern.model.Book;
import com.example.ValidatorPattern.model.ReadingRoom;
import com.example.ValidatorPattern.service.validators.ReadingRoomValidationService;
import com.example.ValidatorPattern.validator.BookValidator;
import com.example.ValidatorPattern.validator.ReadingRoomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReadingRoomValidationServiceImpl implements ReadingRoomValidationService {

    private final List<ReadingRoomValidator> validators;


    @Override
    public void validate(ReadingRoom readingRoom) {
        validators.forEach(v -> v.validate(readingRoom));
    }
}
