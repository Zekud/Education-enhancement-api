package com.example.Edu.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Edu.backend.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    
}