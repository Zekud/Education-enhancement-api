package com.example.Edu.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Edu.backend.service.AnswerService;

@RestController
@RequestMapping("/question")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

   //  @Bean
   // @GetMapping("/get/{id}")
   // public AnswerResponse getAnswerById(@PathVariable int id) {
   //    return answerService.getAnswerById(id);
   // }

   @PostMapping("/saveAnswer")
   public void saveAnswerForQuestion(@RequestParam int questionId, @RequestParam String answer) {
      // Define the method saveAnswerForQuestion in the AnswerService class
      answerService.saveAnswerForQuestion(questionId, answer);
   }

   // @GetMapping("/get/{id}")
   // public QuestionResponse getQuestionById(@PathVariable int id) {
   //    return answerService.getQuestionById(id);
   // }
}
