package com.example.Edu.backend.dto;

public class AnswerResponse {
    private int answerId;
    private int userId;
    private int questionId;
    private String answerText;
    private String userName;

    // Constructors
    public AnswerResponse() {}

    public AnswerResponse(int answerId, int userId, int questionId, String answerText, String userName) {
        this.answerId = answerId;
        this.userId = userId;
        this.questionId = questionId;
        this.answerText = answerText;
        this.userName = userName;
    }

    // Getters and setters
    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
