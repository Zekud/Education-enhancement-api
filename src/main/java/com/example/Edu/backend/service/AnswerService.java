package com.example.Edu.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Edu.backend.dto.AnswerResponse;
import com.example.Edu.backend.model.Answer;
import com.example.Edu.backend.repository.AnswerRepository;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    public void saveAnswerForQuestion(int questionId, String answerText) {
        // Assuming that the Answer entity has setQuestionId, setAnswerText methods.
        Answer answer = new Answer();
        answer.getQuestion().setId(questionId); // Add setter method for questionId
        answer.setAnswerText(answerText);
        answerRepository.save(answer);
    }



    public AnswerResponse getAnswerById(int id) {
        Optional<Answer> answerOptional = answerRepository.findById(id);
        if (answerOptional.isPresent()) {
            Answer answer = answerOptional.get();
            // Assuming that the Answer entity has getAnswerId, getUserId, getQuestionId, getAnswerText methods.
            return new AnswerResponse(
                answer.getId(),
                answer.getUser().getId(),
                answer.getQuestion().getId(),
                answer.getAnswerText()
            );
            
        } else {
            return null; 
        }
    }

    // Other service methods...
}
