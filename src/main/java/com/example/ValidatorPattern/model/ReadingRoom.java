package com.example.ValidatorPattern.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "reading_rooms")
@Entity
public class ReadingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "name")
    String name;
    @Column(name = "available")
    Boolean isAvailable;
}
