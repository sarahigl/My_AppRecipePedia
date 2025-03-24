package com.example.myapplication.Model.IA;

public class Message {
    private int idMessage;
    private String message;
    private String date;
    private int type;
    private int idUser;
    private String title;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public int getIdUser() {
        return idUser;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
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
