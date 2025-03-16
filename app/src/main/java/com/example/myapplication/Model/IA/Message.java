package com.example.myapplication.Model.IA;

public class Message {
    private int idMessage;
    private String message;
    private String date;
    private int type;
    private int idUser;

    public int getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    public Message() {
    }
    public Message(String message, String date, int type, int idUser) {
        this.message = message;
        this.date = date;
        this.type = type;
        this.idUser = idUser;

    }
    public Message(String message, String date) {
        this.message = message;
        this.date = date;
    }

}
