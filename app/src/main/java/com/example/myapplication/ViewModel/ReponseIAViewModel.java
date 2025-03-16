package com.example.myapplication.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReponseIAViewModel extends ViewModel {

    private final MutableLiveData<String> corpReponse = new MutableLiveData<>();
    private final MutableLiveData<String> dateReponse = new MutableLiveData<>();
    private final MutableLiveData<String> idRequeteIA = new MutableLiveData<>();

    public void setCorpReponse(String corp) {
        corpReponse.setValue(corp);
    }

    public MutableLiveData<String> getCorpReponse() {
        return corpReponse;
    }

    public MutableLiveData<String> getDateReponse() {
        return dateReponse;
    }

    public MutableLiveData<String> getIdRequeteIA() {
        return idRequeteIA;
    }
}
