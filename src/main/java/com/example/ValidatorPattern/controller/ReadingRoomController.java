package com.example.ValidatorPattern.controller;

import com.example.ValidatorPattern.dto.ReadBookDto;
import com.example.ValidatorPattern.model.ReadingRoom;
import com.example.ValidatorPattern.service.ReadingRoomService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/readingRoom")
@AllArgsConstructor
public class ReadingRoomController {

    @Autowired
    ReadingRoomService readingRoomService;

    @GetMapping("/status")
    ResponseEntity<Boolean> getCurrentStatus(){
        return ResponseEntity.ok(readingRoomService.currentStatus());
    }

    @PostMapping("/create")
    ResponseEntity<ReadingRoom> create(@RequestBody ReadingRoom readingRoom){
        return ResponseEntity.ok(readingRoomService.save(readingRoom));
    }

    @GetMapping("/list")

    public ResponseEntity<List<ReadingRoom>> getList() {
        return ResponseEntity.ok(readingRoomService.getAll());
    }

    @GetMapping("/showAvailable")
    public ResponseEntity<List<ReadingRoom>> getAllAvailable(){
        return ResponseEntity.ok(readingRoomService.getAllAvailable());
    }

    //TODO finish it
    @PostMapping("readBookInAnyRoom")
    public ResponseEntity<Boolean> readBookInAnyRoom(@RequestBody ReadBookDto readBookDto){
        return ResponseEntity.ok(Boolean.TRUE);
    }

    //TODO finish it
    @PostMapping("readBookSelectedRoom")
    public ResponseEntity<Boolean> readBookSelectedRoom(){
        return ResponseEntity.ok(Boolean.TRUE);
    }

}
