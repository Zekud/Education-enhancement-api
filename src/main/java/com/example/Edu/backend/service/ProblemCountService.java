package com.example.Edu.backend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Edu.backend.repository.ProblemCountRepository;
// import com.example.Edu.backend.repository.UserRepository;
// import com.example.Edu.backend.repository.Quiz;
import com.example.Edu.backend.model.ProblemCount;


@Service
public class ProblemCountService {
    


    @Autowired
    private ProblemCountRepository quiz;


    public ProblemCount submitAnswer(ProblemCount problemCount) {  
        return quiz.save(problemCount);
    }


    public java.util.List <ProblemCount> findByUserId(int userId) {
        java.util.List <ProblemCount> problems = quiz.findByUserId(userId);
        return problems;
    }

}
