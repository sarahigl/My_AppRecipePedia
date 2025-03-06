package com.example.myapplication.Model.IA;

public class FavorisIA {
    private int idFavorisIA;
    private String dateAjout;
    private int idUtilisateur;
    private int idReponseIA;

    public int getIdFavorisIA() {
        return idFavorisIA;
    }

    public void setIdFavorisIA(int idFavorisIA) {
        this.idFavorisIA = idFavorisIA;
    }

    public String getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(String dateAjout) {
        this.dateAjout = dateAjout;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdReponseIA() {
        return idReponseIA;
    }

    public void setIdReponseIA(int idReponseIA) {
        this.idReponseIA = idReponseIA;
    }

    public FavorisIA() {
    }

    public FavorisIA(String dateAjout, int idUtilisateur, int idReponseIA, int idFavorisIA) {
        this.dateAjout = dateAjout;
        this.idUtilisateur = idUtilisateur;
        this.idReponseIA = idReponseIA;
        this.idFavorisIA = idFavorisIA;
    }
}
