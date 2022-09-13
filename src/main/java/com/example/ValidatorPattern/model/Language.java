package com.example.ValidatorPattern.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "languages")
@Entity
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    private String name;
}
