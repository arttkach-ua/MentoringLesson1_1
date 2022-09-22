package com.example.ValidatorPattern.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class BookDto {
    private Integer pages;
    private String author;
    private String name;
    private int languageId;
}
