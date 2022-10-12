package com.example.ValidatorPattern.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ReadBookDto {
    private int userId;
    private int bookId;
    private int readingRoomId;
}
