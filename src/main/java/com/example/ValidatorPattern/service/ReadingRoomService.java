package com.example.ValidatorPattern.service;

import com.example.ValidatorPattern.model.ReadingRoom;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ReadingRoomService {
    Boolean currentStatus(int roomId);

    Map<ReadingRoom, Boolean> currentStatus();

    ReadingRoom save(ReadingRoom readingRoom);

    List<ReadingRoom> getAll();

    List<ReadingRoom> getAllAvailable();

    Optional<ReadingRoom> findByName(String name);

    ReadingRoom findAnyFreeRoom();

    void freeRoomWithDelay(ReadingRoom readingRoom);

    ReadingRoom create(ReadingRoom readingRoom);

    /**
     * Sets available to reading room to false
     * If reading room is not available throws validation exception
     * @param readingRoom
     */
    void bookReadingRoom(ReadingRoom readingRoom);
}
