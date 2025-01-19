package com.example.myapplication.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RequeteIAViewModel extends ViewModel {

    private final MutableLiveData<String> corpRequete = new MutableLiveData<>();
    private final MutableLiveData<String> dateRequete = new MutableLiveData<>();
    private final MutableLiveData<Boolean> statusRequete = new MutableLiveData<>();
    private final MutableLiveData<String> idUtilisateur = new MutableLiveData<>();

    public void setCorpRequete(String corp) {
        corpRequete.setValue(corp);
    }

    public LiveData<String> getCorpRequete() {
        return corpRequete;
    }

    public void setDateRequete(String date) {
        dateRequete.setValue(date);
    }

    public LiveData<String> getDateRequete() {
        return dateRequete;
    }

    public void setStatusRequete(Boolean status) {
        statusRequete.setValue(status);
    }

    public LiveData<Boolean> getStatusRequete() {
        return statusRequete;
    }

    public void setIdUtilisateur(String id) {
        idUtilisateur.setValue(id);
    }

    public LiveData<String> getIdUtilisateur() {
        return idUtilisateur;
    }

}
