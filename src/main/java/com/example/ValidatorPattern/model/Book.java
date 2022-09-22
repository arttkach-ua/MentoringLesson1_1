package com.example.ValidatorPattern.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "books")
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "pages")
    private Integer pages;

    @Column(name = "author")
    private String author;

    @ManyToOne
    @JoinColumn(name="language_id")
    private Language language;

}
