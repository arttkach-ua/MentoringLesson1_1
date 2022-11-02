package com.example.ValidatorPattern.service.impl;

import com.example.ValidatorPattern.exceptions.ValidationException;
import com.example.ValidatorPattern.model.ReadingRoom;
import com.example.ValidatorPattern.reposithory.ReadingRoomRepository;
import com.example.ValidatorPattern.service.ReadingRoomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ReadingRoomServiceImplTest {
    @MockBean
    ReadingRoomRepository readingRoomRepository;

    @Autowired
    ReadingRoomService readingRoomService;

    @Test
    void currentStatus() {
        List<ReadingRoom> allRooms = getTestReadingRooms();
        ReadingRoom room2 = allRooms.get(1);

        when(readingRoomRepository.findAll())
                .thenReturn(allRooms);
        Map<ReadingRoom, Boolean> statuses = readingRoomService.currentStatus();
        ;
        assertEquals(3, statuses.size());
        assertEquals(statuses.get(room2).booleanValue(),true);
    }

    @Test
    void save() {
        ReadingRoom testRoom = new ReadingRoom(1,"room1",true);
        when(readingRoomRepository.save(any(ReadingRoom.class)))
                .thenReturn(testRoom);
        readingRoomService.save(testRoom);
        verify(readingRoomRepository,times(1))
                .save(any(ReadingRoom.class));
    }

    @Test
    void getAll() {
        List<ReadingRoom> allRooms = getTestReadingRooms();

        when(readingRoomRepository.findAll())
                .thenReturn(allRooms);
        Map<ReadingRoom, Boolean> statuses = readingRoomService.currentStatus();
        assertEquals(3, statuses.size());
    }

    @Test
    void getAllAvailable() {
        List<ReadingRoom> allRooms = getTestAvailableReadingRooms();

        when(readingRoomRepository.findAll(any(Example.class)))
                .thenReturn(allRooms);
        List<ReadingRoom> availableRooms = readingRoomService.getAllAvailable();
        assertEquals(2, availableRooms.size());
    }

    @Test
    void findByNameEntityIsPresentInDB() {
        ReadingRoom room = new ReadingRoom(1,"room1",true);
        when(readingRoomRepository.getByName(anyString()))
                .thenReturn(Optional.of(room));
        Optional<ReadingRoom> resultRoom = readingRoomService.findByName("room1");
        assertTrue(resultRoom.isPresent());
        assertEquals("room1", resultRoom.get().getName());
        verify(readingRoomRepository, times(1)).getByName("room1");
    }

    @Test
    void findByNameEntityNotPresentInDB() {
        when(readingRoomRepository.getByName(anyString()))
                .thenReturn(Optional.empty());
        Optional<ReadingRoom> resultRoom = readingRoomService.findByName("room1");
        assertFalse(resultRoom.isPresent());
        verify(readingRoomRepository, times(1)).getByName("room1");
    }

    @Test
    void createShouldCreateNewRoom() {
        ReadingRoom room = new ReadingRoom(1,"room1",true);
        when(readingRoomRepository.save(any(ReadingRoom.class)))
                .thenReturn(room);
        ReadingRoom resultRoom = readingRoomService.create(room);
        assertEquals(room, resultRoom);
    }

    @Test
    void createShouldFailValidationRoomExists() {
        ReadingRoom room = new ReadingRoom(1,"room1",true);
        when(readingRoomRepository.save(any(ReadingRoom.class)))
                .thenReturn(room);
        when(readingRoomRepository.getByName(anyString()))
                .thenReturn(Optional.of(room));
        ValidationException ex = assertThrows(ValidationException.class, ()->readingRoomService.create(room));

    }

    private List<ReadingRoom> getTestReadingRooms(){
        ReadingRoom room1 = new ReadingRoom(1,"room1",true);
        ReadingRoom room2 = new ReadingRoom(2,"room2",true);
        ReadingRoom room3 = new ReadingRoom(3,"room3",false);
        return List.of(room1,room2,room3);
    }

    private List<ReadingRoom> getTestAvailableReadingRooms(){
        ReadingRoom room1 = new ReadingRoom(1,"room1",true);
        ReadingRoom room2 = new ReadingRoom(2,"room2",true);
        return List.of(room1,room2);
    }


    @Test
    void bookReadingRoomPositiveCase() {
        ReadingRoom room1 = new ReadingRoom(1,"room1",true);
        when(readingRoomRepository.save(any(ReadingRoom.class)))
                .thenReturn(room1);
        assertDoesNotThrow(()->readingRoomService.bookReadingRoom(room1));
        assertFalse(room1.getIsAvailable());
        verify(readingRoomRepository,times(1)).save(room1);
    }

    @Test
    void bookBusyReadingRoomShouldThrowException() {
        ReadingRoom room1 = new ReadingRoom(1,"room1",false);
        when(readingRoomRepository.save(any(ReadingRoom.class)))
                .thenReturn(room1);
        ValidationException ex = assertThrows(ValidationException.class, ()->readingRoomService.bookReadingRoom(room1));
        assertEquals("Room room1 is busy", ex.getMessage());
        verify(readingRoomRepository,times(0)).save(room1);
    }
}