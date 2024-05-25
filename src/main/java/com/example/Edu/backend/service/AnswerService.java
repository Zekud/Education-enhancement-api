package com.example.Edu.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Edu.backend.dto.AnswerResponse;
import com.example.Edu.backend.model.Answer;
import com.example.Edu.backend.model.Question;
import com.example.Edu.backend.model.User;
import com.example.Edu.backend.repository.AnswerRepository;
import com.example.Edu.backend.repository.QuestionRepository;
import com.example.Edu.backend.repository.UserRepository;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private UserRepository userRepository;
    
    public ResponseEntity<?> saveAnswerForQuestion(int questionId, String answerText, int userId) {
        Answer answer = new Answer();
        
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if (!questionOptional.isPresent()) {
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body("Question not found");
        }
        answer.setQuestion(questionOptional.get());
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist");
        }
        answer.setUser(userOptional.get());

        answer.setAnswerText(answerText);
        answerRepository.save(answer);
        return ResponseEntity.status(HttpStatus.CREATED).body("Answer Posted successfully");
    }



    public ResponseEntity<?> getAnswerById(int id) {
        Optional<Answer> answerOptional = answerRepository.findById(id);
        if (answerOptional.isPresent()) {
            Answer answer = answerOptional.get();
            // Assuming that the Answer entity has getAnswerId, getUserId, getQuestionId, getAnswerText methods.
           return new ResponseEntity<AnswerResponse>(new AnswerResponse(answer.getId(), answer.getUser().getId(), answer.getQuestion().getId(), answer.getAnswerText()), HttpStatus.OK);
            
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Answer not found"); 
        }
    }

    public ResponseEntity<?> getAnswersByQuestionId(int questionId) {
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if (!questionOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question not found");
        }
        List<Answer> answers = answerRepository.findByQuestionId(questionId);
        List<AnswerResponse> answerResponse = new ArrayList<>();
        for (Answer answer : answers) {
            // Assuming that the Answer entity has getAnswerId, getUserId, getQuestionId, getAnswerText methods.
            answerResponse.add(new AnswerResponse(answer.getId(), answer.getUser().getId(), answer.getQuestion().getId(), answer.getAnswerText()));
        }
        if (answerResponse.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No answers found for this question");
        }
        return ResponseEntity.status(HttpStatus.OK).body(answerResponse);

    
    }
    // Other service methods...
}
