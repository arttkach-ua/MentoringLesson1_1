package com.example.ValidatorPattern.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ReadBookDto {
    private int userId;
    private int bookId;
    private int readingRoomId;
}
