package com.example.ValidatorPattern.validator;

import com.example.ValidatorPattern.model.ReadingRoom;
import com.example.ValidatorPattern.model.readBook.ReadBook;

public interface ReadingRoomValidator {
    void validate(ReadingRoom readingRoom);
}
