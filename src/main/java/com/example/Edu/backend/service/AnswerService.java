package com.example.Edu.backend.service;
import com.example.Edu.backend.model.Answer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Edu.backend.repository.AnswerRepository;
@Service
public class AnswerService {
    @Autowired
    private  AnswerRepository answerRepository;


    public List <Answer> getAnswers() {
        return answerRepository.findAll();
    }
//  i will check it out
    @SuppressWarnings("unchecked")
    public List <Answer> getAnswer(Integer id) {
        return (List<Answer>) answerRepository.findById(id).get();
    }

    public Answer saveAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public void deleteAnswer(Integer id) {
        answerRepository.deleteById(id);
    }


}
