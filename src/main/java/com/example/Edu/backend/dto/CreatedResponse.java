package com.example.Edu.backend.dto;

public class CreatedResponse {
    private String message;

    public CreatedResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
