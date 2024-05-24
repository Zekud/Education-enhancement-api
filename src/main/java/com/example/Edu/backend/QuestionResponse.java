package com.example.Edu.backend;

public class QuestionResponse {
    private int questionId;
    private int userId;
    private String questionText;


    
    public QuestionResponse() {
    }
    public int getQuestionId() {
        return questionId;
    }
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getQuestionText() {
        return questionText;
    }
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    // getters and setters
}