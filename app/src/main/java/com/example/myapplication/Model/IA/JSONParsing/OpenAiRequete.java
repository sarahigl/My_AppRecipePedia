package com.example.myapplication.Model.IA.JSONParsing;


import com.example.myapplication.Model.IA.DTO.MessageDTO;

import java.util.List;

public class OpenAiRequete {
    private String model;
    private List<MessageDTO> messages;

    public OpenAiRequete() {
    }

    public OpenAiRequete(String model, List<MessageDTO> messageDTOS) {
        this.model = model;
        this.messages = messageDTOS;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDTO> messageDTOS) {
        this.messages = messageDTOS;
    }
}
