package com.example.ValidatorPattern.reposithory;

import com.example.ValidatorPattern.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}