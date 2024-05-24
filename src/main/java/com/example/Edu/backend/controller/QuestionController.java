package com.example.Edu.backend.controller;

import java.util.List;

import com.example.Edu.backend.QuestionRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
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
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/get/{id}")
    public Question getQuestionById(@PathVariable int id) {
        return questionService.getQuestionById(id);
    }

    // @PostMapping("/create")
    // public ResponseEntity<String> createQuestion(@RequestBody QuestionRequest questionRequest) {
    //     User user = userService.getById(questionRequest.getUserId());
    //     if (user == null) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
    //     }
    //     Question question = new Question();
    //     question.setUser(user);
    //     question.setQuestionText(questionRequest.getQuestionText());
    //     questionService.createQuestion(question);
    //     return ResponseEntity.status(HttpStatus.OK).body("Question created successfully");
    // }
    @PostMapping("/create/{userId}")
    public ResponseEntity<String> createQuestion(@PathVariable int userId, @RequestBody Map<String, Object> payload) {
    String questionText = (String) payload.get("questionText");
    User user = userService.getById(userId);
    if (user == null) {
        // handle error
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
    }
    Question question = new Question();
    question.setUser(user);
    question.setQuestionText(questionText);
    questionService.createQuestion(question);
    return ResponseEntity.status(HttpStatus.OK).body("Question created successfully");
}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable int id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
    }

}
