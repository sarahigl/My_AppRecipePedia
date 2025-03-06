package com.example.myapplication.Model.IA.DTO;

public class Message {
    private String role;
    private String content;

    // Constructor
    public Message(String role, String content) {
        this.role = role;
        this.content = content;
    }

    // Getters
    public String getRole() {
        return role;
    }

    public String getContent() {
        return content;
    }

    // Setters
    public void setRole(String role) {
        this.role = role;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
