package com.example.ValidatorPattern.controller;

import com.example.ValidatorPattern.dto.ReadBookDto;
import com.example.ValidatorPattern.model.ReadingRoom;
import com.example.ValidatorPattern.service.BookProcessingService;
import com.example.ValidatorPattern.service.ReadingRoomService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/readingRoom")
@AllArgsConstructor
public class ReadingRoomController {

    @Autowired
    ReadingRoomService readingRoomService;

    @Autowired
    BookProcessingService processingService;

    @GetMapping("/status")
    ResponseEntity<Map<ReadingRoom, Boolean>> getCurrentStatus(){
        return ResponseEntity.ok(readingRoomService.currentStatus());
    }

    @PostMapping("/create")
    ResponseEntity<ReadingRoom> create(@RequestBody ReadingRoom readingRoom){
        return ResponseEntity.ok(readingRoomService.create(readingRoom));
    }

    @GetMapping("/list")

    public ResponseEntity<List<ReadingRoom>> getList() {
        return ResponseEntity.ok(readingRoomService.getAll());
    }

    @GetMapping("/showAvailable")
    public ResponseEntity<List<ReadingRoom>> getAllAvailable(){
        return ResponseEntity.ok(readingRoomService.getAllAvailable());
    }


    @PostMapping("readBookInAnyRoom")
    public ResponseEntity<Boolean> readBookInAnyRoom(@RequestBody ReadBookDto readBookDto){
        return ResponseEntity.ok(processingService.readBookInAnyReadingRoom(readBookDto));
    }

    //TODO finish it
    @PostMapping("readBookSelectedRoom")
    public ResponseEntity<Boolean> readBookSelectedRoom(){
        return ResponseEntity.ok(Boolean.TRUE);
    }

}
