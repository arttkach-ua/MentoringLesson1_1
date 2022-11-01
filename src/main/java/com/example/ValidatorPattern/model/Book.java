package com.example.ValidatorPattern.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "books")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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
