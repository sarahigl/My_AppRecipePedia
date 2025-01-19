package com.example.myapplication.Model;

public class Utilisateur {
    private int idUtilisateur;
    private String pseudoUtilisateur;
    private String emailUtilisateur;
    private String nomUtilisateur;
    private String prenomUtilisateur;
    private String mdpUtilisateur;

    public Utilisateur() {
    }

    public Utilisateur(String pseudoUtilisateur, String emailUtilisateur, String nomUtilisateur, String mdpUtilisateur, String prenomUtilisateur) {
        this.pseudoUtilisateur = pseudoUtilisateur;
        this.emailUtilisateur = emailUtilisateur;
        this.nomUtilisateur = nomUtilisateur;
        this.mdpUtilisateur = mdpUtilisateur;
        this.prenomUtilisateur = prenomUtilisateur;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getPseudoUtilisateur() {
        return pseudoUtilisateur;
    }

    public void setPseudoUtilisateur(String pseudoUtilisateur) {
        this.pseudoUtilisateur = pseudoUtilisateur;
    }

    public String getEmailUtilisateur() {
        return emailUtilisateur;
    }

    public void setEmailUtilisateur(String emailUtilisateur) {
        this.emailUtilisateur = emailUtilisateur;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getPrenomUtilisateur() {
        return prenomUtilisateur;
    }

    public void setPrenomUtilisateur(String prenomUtilisateur) {
        this.prenomUtilisateur = prenomUtilisateur;
    }

    public String getMdpUtilisateur() {
        return mdpUtilisateur;
    }

    public void setMdpUtilisateur(String mdpUtilisateur) {
        this.mdpUtilisateur = mdpUtilisateur;
    }
}
