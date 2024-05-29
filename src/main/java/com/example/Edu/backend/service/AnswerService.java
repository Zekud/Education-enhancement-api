package com.example.Edu.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Edu.backend.dto.AnswerResponse;
import com.example.Edu.backend.dto.ErrorResponse;
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
            return new ResponseEntity<>(new ErrorResponse("Question not found"), HttpStatus.NOT_FOUND);
        }
        answer.setQuestion(questionOptional.get());
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(new ErrorResponse("User not found"), HttpStatus.NOT_FOUND);
        }
        answer.setUser(userOptional.get());

        answer.setAnswerText(answerText);
        answerRepository.save(answer);
        return new ResponseEntity<>(new AnswerResponse(answer.getId(), answer.getUser().getId(), answer.getQuestion().getId(), answer.getAnswerText(), answer.getUser().getFname()), HttpStatus.CREATED);
    }



    public ResponseEntity<?> getAnswerById(int id) {
        Optional<Answer> answerOptional = answerRepository.findById(id);
        if (answerOptional.isPresent()) {
            Answer answer = answerOptional.get();
            // Assuming that the Answer entity has getAnswerId, getUserId, getQuestionId, getAnswerText methods.
           return new ResponseEntity<AnswerResponse>(new AnswerResponse(answer.getId(), answer.getUser().getId(), answer.getQuestion().getId(), answer.getAnswerText(), answer.getUser().getFname()), HttpStatus.OK);
            
        } else {
            return new ResponseEntity<>(new ErrorResponse("Answer not found"), HttpStatus.NOT_FOUND); 
        }
    }

    public ResponseEntity<?> getAnswersByQuestionId(int questionId) {
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if (!questionOptional.isPresent()) {
            return new ResponseEntity<>(new ErrorResponse("Question not found"), HttpStatus.NOT_FOUND);
        }
        List<Answer> answers = answerRepository.findByQuestionId(questionId);
        List<AnswerResponse> answerResponse = new ArrayList<>();
        for (Answer answer : answers) {
            // Assuming that the Answer entity has getAnswerId, getUserId, getQuestionId, getAnswerText methods.
            answerResponse.add(new AnswerResponse(answer.getId(), answer.getUser().getId(), answer.getQuestion().getId(), answer.getAnswerText(), answer.getUser().getFname()));
        }
        if (answerResponse.isEmpty()) {
            return new ResponseEntity<>(new ErrorResponse("No answers found"), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(answerResponse);

    
    }
    // Other service methods...
}
