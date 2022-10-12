package com.example.ValidatorPattern.validator.readingRoom;

import com.example.ValidatorPattern.exceptions.ValidationException;
import com.example.ValidatorPattern.model.ReadingRoom;
import com.example.ValidatorPattern.reposithory.ReadingRoomRepository;
import com.example.ValidatorPattern.validator.ReadingRoomValidator;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Data
@AllArgsConstructor
public class NameValidator implements ReadingRoomValidator {

    @Autowired
    ReadingRoomRepository readingRoomRepository;

    @Override
    public void validate(ReadingRoom readingRoom) {
        Optional.ofNullable(readingRoom.getName())
                .filter(this::readingRoomExists)
                .orElseThrow(()->{
                    return this.constructValidationException(readingRoom.getName());
                });
    }

    private boolean readingRoomExists(String name) {
        return !readingRoomRepository.getByName(name).isPresent();
    }

    private ValidationException constructValidationException(String name) {
        return new ValidationException(String.format("Room with name %s exists",name));
    }
}
