package com.example.Edu.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Edu.backend.dto.AnswerRequest;
import com.example.Edu.backend.service.AnswerService;

@RestController
@RequestMapping("/answer")
@CrossOrigin("http://localhost:5173")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

   //  @Bean
   @GetMapping("/getAnswer/{id}")
   public ResponseEntity<?> getAnswerById(@PathVariable int id) {
      return answerService.getAnswerById(id);
   }

   @PostMapping("/saveAnswer")
   public ResponseEntity<?> saveAnswerForQuestion(@RequestBody AnswerRequest answerRequest) {
      int questionId = answerRequest.getQuestionId();
      String answer = answerRequest.getAnswer();
      int userId = answerRequest.getUserId();
      // Define the method saveAnswerForQuestion in the AnswerService class
      return answerService.saveAnswerForQuestion(questionId, answer, userId);
   }

   @GetMapping("/question/{questionId}")
   public ResponseEntity<?> getAnswersByQuestionId(@PathVariable int questionId) {
      return answerService.getAnswersByQuestionId(questionId);
   }
   // @GetMapping("/get/{id}")
   // public QuestionResponse getQuestionById(@PathVariable int id) {
   //    return answerService.getQuestionById(id);
   // }
}
