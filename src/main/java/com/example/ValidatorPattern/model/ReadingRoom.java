package com.example.ValidatorPattern.model;

import lombok.*;

import javax.persistence.*;

@Table(name = "reading_rooms")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReadingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "name")
    String name;
    @Column(name = "available")
    Boolean isAvailable;
}
