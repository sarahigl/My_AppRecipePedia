package com.example.myapplication.Model;

public class RequeteIA {

    private int idRequeteIA;
    private String corpRequete;
    private String dateRequete;
    private Boolean statusRequete;
    private int idUtilisateur;

    public RequeteIA() {
    }

    public RequeteIA(String corpRequete, String dateRequete, Boolean statusRequete, int idUtilisateur) {
        this.corpRequete = corpRequete;
        this.dateRequete = dateRequete;
        this.statusRequete = statusRequete;
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdRequeteIA() {
        return idRequeteIA;
    }

    public void setIdRequeteIA(int idRequeteIA) {
        this.idRequeteIA = idRequeteIA;
    }

    public String getCorpRequete() {
        return corpRequete;
    }

    public void setCorpRequete(String corpRequete) {
        this.corpRequete = corpRequete;
    }

    public String getDateRequete() {
        return dateRequete;
    }

    public void setDateRequete(String dateRequete) {
        this.dateRequete = dateRequete;
    }

    public Boolean getStatusRequete() {
        return statusRequete;
    }

    public void setStatusRequete(Boolean statusRequete) {
        this.statusRequete = statusRequete;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
}
