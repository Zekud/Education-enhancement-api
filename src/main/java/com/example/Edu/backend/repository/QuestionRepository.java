package com.example.Edu.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Edu.backend.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    
} 