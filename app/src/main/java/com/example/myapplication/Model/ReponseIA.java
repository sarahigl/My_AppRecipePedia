package com.example.myapplication.Model;

public class ReponseIA {
    private int idReponseIA;
    private String corpReponse;
    private String dateReponse;
    private int idRequeteIA;

    public ReponseIA() {
    }

    public ReponseIA(String corpReponse, String dateReponse, int idRequeteIA) {
        this.corpReponse = corpReponse;
        this.dateReponse = dateReponse;
        this.idRequeteIA = idRequeteIA;
    }

    public int getIdReponseIA() {
        return idReponseIA;
    }

    public void setIdReponseIA(int idReponseIA) {
        this.idReponseIA = idReponseIA;
    }

    public String getCorpReponse() {
        return corpReponse;
    }

    public void setCorpReponse(String corpReponse) {
        this.corpReponse = corpReponse;
    }

    public int getIdRequeteIA() {
        return idRequeteIA;
    }

    public void setIdRequeteIA(int idRequeteIA) {
        this.idRequeteIA = idRequeteIA;
    }

    public String getDateReponse() {
        return dateReponse;
    }

    public void setDateReponse(String dateReponse) {
        this.dateReponse = dateReponse;
    }
}
