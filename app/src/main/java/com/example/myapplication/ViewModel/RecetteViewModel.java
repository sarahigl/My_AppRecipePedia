package com.example.myapplication.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RecetteViewModel extends ViewModel {

    private final MutableLiveData<String> imageURL = new MutableLiveData<>();
    private final MutableLiveData<String> titre = new MutableLiveData<>();
    private final MutableLiveData<String> ingredient = new MutableLiveData<>();
    private final MutableLiveData<String> description = new MutableLiveData<>();
    private final MutableLiveData<String> tempsCuisson = new MutableLiveData<>();

    public void setImageURL(String url) {
        imageURL.setValue(url);
    }

    public LiveData<String> getImageURL() {
        return imageURL;
    }

    public void setTitre(String t) {
        titre.setValue(t);
    }

    public LiveData<String> getTitre() {
        return titre;
    }

    public void setIngredient(String i) {
        ingredient.setValue(i);
    }

    public LiveData<String> getIngredient() {
        return ingredient;
    }

    public void setDescription(String d) {
        description.setValue(d);
    }

    public LiveData<String> getDescription() {
        return description;
    }

    public void setTempsCuisson(String t) {
        tempsCuisson.setValue(t);
    }

    public LiveData<String> getTempsCuisson() {
        return tempsCuisson;
    }
}
