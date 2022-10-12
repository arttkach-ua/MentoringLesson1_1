package com.example.ValidatorPattern.reposithory;

import com.example.ValidatorPattern.model.ReadingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReadingRoomRepository extends JpaRepository<ReadingRoom, Integer> {

    Optional<ReadingRoom> getByName(String name);

}
