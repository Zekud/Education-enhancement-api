package com.example.Edu.backend.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
// import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Edu.backend.service.ProblemCountService;
import com.example.Edu.backend.dto.QuizResponse;
import com.example.Edu.backend.model.ProblemCount;
import com.example.Edu.backend.model.User;
import com.example.Edu.backend.repository.UserRepository;

@RestController
@RequestMapping("/quiz")
public class ProblemCountController {

    @Autowired
    private ProblemCountService problemCountService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/submit")
    public ResponseEntity<?> createProblemCount(@RequestBody java.util.Map<String, String> body) {
        int userId = Integer.parseInt(body.get("userId"));
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        User user = userRepository.findById(userId).orElse(null);


        ProblemCount problemCount = new ProblemCount();
        problemCount.setUser(user);
        problemCount.setSubject(body.get("subject"));
        problemCount.setCount(Integer.parseInt(body.get("count")));
        problemCount.setYear(body.get("year"));
        // set other fields of problemCount

       problemCountService.submitAnswer(problemCount);
        return ResponseEntity.status(HttpStatus.OK).body("Answer submitted successfully");
    }

    // @PostMapping("/submit")
    // public ResponseEntity<?> submitAnswer(@RequestBody ProblemCount problem ) {
    //     ProblemCount problemCount = new ProblemCount();

    //     problemCount.setUser(problem.getUser());
    //     problemCount.setSubject(problem.getSubject());
    //     problemCount.setCount(problem.getCount());
    //     problemCount.setYear(problem.getYear());
        
    //     problemCountService.submitAnswer(problemCount);
    //     return ResponseEntity.status(HttpStatus.OK).body("Answer submitted successfully");

    // }

    @GetMapping("/result/{userId}")
    public ResponseEntity<?> getResult(@PathVariable int userId) {
        List<ProblemCount> problems = problemCountService.findByUserId(userId);
        List<QuizResponse> quizResponses = new java.util.ArrayList<>();
        for (ProblemCount problem : problems) {
            QuizResponse quizResponse = new QuizResponse();
            quizResponse.setUserId(problem.getUser().getId());
            quizResponse.setSubject(problem.getSubject());
            quizResponse.setCount(problem.getCount());
            quizResponse.setYear(problem.getYear());
            quizResponses.add(quizResponse);
        }
        return ResponseEntity.status(HttpStatus.OK).body(quizResponses);
        
    }
    
}
