package com.example.myapplication.Model.IA.JSONParsing;


import com.example.myapplication.Model.IA.DTO.Message;

import java.util.List;

public class OpenAiRequete {
    private String model;
    private List<Message> messages;

    public OpenAiRequete() {
    }

    public OpenAiRequete(String model, List<Message> messages) {
        this.model = model;
        this.messages = messages;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
