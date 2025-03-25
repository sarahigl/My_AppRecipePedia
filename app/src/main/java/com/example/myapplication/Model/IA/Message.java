package com.example.myapplication.Model.IA;

public class Message {
    public static final int TYPE_USER = 0;
    public static final int TYPE_BOT = 1;

    private int idMessage;
    private String message;
    private String date;
    private int type;
    private int idUser;
    private String title;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

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

    public Message(String message, String date) {
        this.message = message;
        this.date = date;
    }
    public Message(String message, int type) {
        this.message = message;
        this.type = type;
    }

}
