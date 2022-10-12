package com.example.ValidatorPattern.service.impl;

import com.example.ValidatorPattern.exceptions.ValidationException;
import com.example.ValidatorPattern.model.ReadingRoom;
import com.example.ValidatorPattern.reposithory.ReadingRoomRepository;
import com.example.ValidatorPattern.service.ReadingRoomService;
import com.example.ValidatorPattern.service.UtilService;
import com.example.ValidatorPattern.service.validators.BookValidatorService;
import com.example.ValidatorPattern.service.validators.ReadingRoomValidationService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@AllArgsConstructor
public class ReadingRoomServiceImpl implements ReadingRoomService {
    private final ReadingRoomValidationService validatorService;
    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    UtilService utilService;

    @Autowired
    ReadingRoomRepository readingRoomRepository;

    @Override
    public Boolean currentStatus(){
        ReadingRoom readingRoom = applicationContext.getBean(ReadingRoom.class);
        if (readingRoom.getIsAvailable()) {
            readingRoom.setIsAvailable(false);
            utilService.freeRoom();
        }else {
            throw new ValidationException("Room is busy");
        }
        return readingRoom.getIsAvailable();
    }

    @Override
    public ReadingRoom save(ReadingRoom readingRoom) {
        validatorService.validate(readingRoom);
        setDefaulValuesToReadingRoom(readingRoom);
        return readingRoomRepository.save(readingRoom);
    }

    private static void setDefaulValuesToReadingRoom(ReadingRoom readingRoom) {
        if (readingRoom.getIsAvailable()==null){
            readingRoom.setIsAvailable(true);
        }
    }

    @Override
    public List<ReadingRoom> getAll() {
        return readingRoomRepository.findAll();
    }

    @Override
    public List<ReadingRoom> getAllAvailable() {
        ReadingRoom readingRoom = new ReadingRoom();
        readingRoom.setIsAvailable(true);

        Example<ReadingRoom> example = Example.of(readingRoom);
        return readingRoomRepository.findAll(example);
    }

    @Override
    public Optional<ReadingRoom> findByName(String name) {
        return readingRoomRepository.getByName(name);
    }

    @Override
    public ReadingRoom findAnyFreeRoom() {
        List<ReadingRoom> freeRooms = getAllAvailable();
        if (freeRooms.size()==0) throw new ValidationException("No free reading rooms");
        return freeRooms.get(0);
    }
}
