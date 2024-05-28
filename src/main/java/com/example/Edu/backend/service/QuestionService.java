package com.example.Edu.backend.service;
import java.util.ArrayList;
import java.util.List;

import com.example.Edu.backend.QuestionResponse;
import com.example.Edu.backend.dto.ErrorResponse;
import com.example.Edu.backend.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Edu.backend.repository.QuestionRepository;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public ResponseEntity<?> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();

        if (questions.isEmpty()) {
            return new ResponseEntity<>(new ErrorResponse("No questions found"), HttpStatus.NOT_FOUND);
        }
        List<QuestionResponse> responses = new ArrayList<>();

        for (Question question : questions) {
            QuestionResponse response = new QuestionResponse();
            response.setQuestionId(question.getId());
            response.setUserId(question.getUser().getId());
            response.setQuestionText(question.getQuestionText());
            response.setQuestionDescription(question.getQuestionDescription());
            responses.add(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    public ResponseEntity<?> getQuestionById(int id) {
        Question question = questionRepository.findById(id).orElse(null);
         if (question == null) {
            return new ResponseEntity<>(new ErrorResponse("Question not found"), HttpStatus.NOT_FOUND);
        }
        QuestionResponse response = new QuestionResponse();
        response.setQuestionId(question.getId());
        response.setUserId(question.getUser().getId());
        response.setQuestionText(question.getQuestionText());
        response.setQuestionDescription(question.getQuestionDescription());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public void deleteQuestion(int id) {
        questionRepository.deleteById(id);
    }
}
