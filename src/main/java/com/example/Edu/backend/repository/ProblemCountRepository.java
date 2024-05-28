package com.example.Edu.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Edu.backend.model.ProblemCount;

public interface  ProblemCountRepository extends JpaRepository <ProblemCount, Integer>{
     List<ProblemCount> findByUserId(int userId);
}
