package com.example.ValidatorPattern.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder

public class BookDto {
    private Integer pages;
    private String author;
    private String name;
    private int languageId;
}
