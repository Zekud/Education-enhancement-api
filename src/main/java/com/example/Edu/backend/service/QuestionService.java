package com.example.Edu.backend.service;
import java.util.ArrayList;
import java.util.List;

import com.example.Edu.backend.QuestionResponse;
import com.example.Edu.backend.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Edu.backend.repository.QuestionRepository;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public List<QuestionResponse> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        List<QuestionResponse> responses = new ArrayList<>();

        for (Question question : questions) {
            QuestionResponse response = new QuestionResponse();
            response.setQuestionId(question.getId());
            response.setUserId(question.getUser().getId());
            response.setQuestionText(question.getQuestionText());
            responses.add(response);
        }
        return responses;
    }

    public QuestionResponse getQuestionById(int id) {
        Question question = questionRepository.findById(id).orElse(null);
         if (question == null) {
            return null;
        }
        QuestionResponse response = new QuestionResponse();
        response.setQuestionId(question.getId());
        response.setUserId(question.getUser().getId());
        response.setQuestionText(question.getQuestionText());
        return response;
    }

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public void deleteQuestion(int id) {
        questionRepository.deleteById(id);
    }
}
