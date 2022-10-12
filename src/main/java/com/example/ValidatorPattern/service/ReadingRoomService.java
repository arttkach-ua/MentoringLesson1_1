package com.example.ValidatorPattern.service;

import com.example.ValidatorPattern.model.ReadingRoom;

import java.util.List;
import java.util.Optional;

public interface ReadingRoomService {
    Boolean currentStatus();

    ReadingRoom save(ReadingRoom readingRoom);

    List<ReadingRoom> getAll();

    List<ReadingRoom> getAllAvailable();

    Optional<ReadingRoom> findByName(String name);

    ReadingRoom findAnyFreeRoom();
}
