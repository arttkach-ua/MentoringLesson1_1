package com.example.ValidatorPattern.validator.readingRoom;

import com.example.ValidatorPattern.exceptions.ValidationException;
import com.example.ValidatorPattern.model.ReadingRoom;
import com.example.ValidatorPattern.reposithory.ReadingRoomRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class NameValidatorTest {

    @MockBean
    ReadingRoomRepository readingRoomRepository;

    @Autowired
    NameValidator nameValidator;

    @Test
    void validatePositiveCase() {
        ReadingRoom room = new ReadingRoom(1,"room1",true);
        when(readingRoomRepository.getByName(anyString()))
                .thenReturn(Optional.empty());
        assertDoesNotThrow(()->nameValidator.validate(room));
    }

    @Test
    void validateRoomIsPresentShouldThrowException() {
        ReadingRoom room = new ReadingRoom(1,"room1",true);
        when(readingRoomRepository.getByName(anyString()))
                .thenReturn(Optional.of(room));
        ValidationException ex = assertThrows(ValidationException.class,()->nameValidator.validate(room));
        assertEquals("Room with name room1 exists", ex.getMessage());
    }
}