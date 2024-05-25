package com.example.Edu.backend.controller;

import com.example.Edu.backend.dto.CreatedResponse;
import com.example.Edu.backend.model.Question;
import com.example.Edu.backend.model.User;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Edu.backend.service.QuestionService;
import com.example.Edu.backend.service.UserService;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getQuestionById(@PathVariable int id) {
       
        return questionService.getQuestionById(id);
    }

   
    @PostMapping("/create/{userId}")
    public ResponseEntity<?> createQuestion(@PathVariable int userId, @RequestBody Map<String, Object> payload) {
    String questionText = (String) payload.get("questionText");
    User user = userService.getById(userId);
    if (user == null) {
        // handle error
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("UnAuthorized User");
    }
    Question question = new Question();
    question.setUser(user);
    question.setQuestionText(questionText);
    questionService.createQuestion(question);
    return new ResponseEntity<>(new CreatedResponse("Question created successfully"), HttpStatus.CREATED);
}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable int id) {
        questionService.deleteQuestion(id);
        return new ResponseEntity<>(new CreatedResponse("Question deleted successfully"), HttpStatus.OK);
    }

}
