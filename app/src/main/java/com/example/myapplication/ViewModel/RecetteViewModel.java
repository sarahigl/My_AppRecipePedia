package com.example.myapplication.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RecetteViewModel extends ViewModel {

    private final MutableLiveData<String> imageURL = new MutableLiveData<>();
    private final MutableLiveData<String> titre = new MutableLiveData<>("Titre par défaut");
    private final MutableLiveData<String> ingredient = new MutableLiveData<>("Ingrédient par défaut");
    private final MutableLiveData<String> description = new MutableLiveData<>("Description par défaut");
    private final MutableLiveData<String> tempsCuisson = new MutableLiveData<>("Temps de cuisson par défaut");

    public void setImageURL(String url) {
        imageURL.setValue(url);
    }

    public LiveData<String> getImageURL() {
        return imageURL;
    }

    public void setTitre(String t) {
        titre.setValue(t);
        Log.d("ViewModel", "Titre updated: " + t);
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

    public RecetteViewModel() {
        Log.d("ViewModel", "ViewModel created. Titre: " + titre.getValue());
    }

}

