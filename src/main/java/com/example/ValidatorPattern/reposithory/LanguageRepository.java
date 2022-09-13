package com.example.ValidatorPattern.reposithory;

import com.example.ValidatorPattern.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
}