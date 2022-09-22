package com.example.ValidatorPattern.reposithory;

import com.example.ValidatorPattern.model.readBook.ReadBook;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReadBookRepository extends JpaRepository<ReadBook, Integer> {
}
