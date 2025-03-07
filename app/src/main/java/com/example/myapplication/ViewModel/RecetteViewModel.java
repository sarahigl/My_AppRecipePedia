package com.example.myapplication.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;

public class RecetteViewModel extends ViewModel {

    private final MutableLiveData<String> imageURL = new MutableLiveData<>();
    private final MutableLiveData<String> titre = new MutableLiveData<>("Titre par défaut");
    private final MutableLiveData<List<String>> ingredients = new MutableLiveData<>();
    //private final List<String> ingredientIds;
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
        //Log.d("ViewModel", "Titre updated: " + t);
    }

    public LiveData<String> getTitre() {
        return titre;
    }

    public MutableLiveData<List<String>> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> newIngredients) {
        ingredients.setValue(newIngredients);
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

   /* public RecetteViewModel(String ingredientIds) {
        this.ingredientIds = Collections.singletonList(ingredientIds);
        Log.d("ViewModel", "ViewModel created. Titre: " + titre.getValue());
    }
    public void addIngredientId(String ingredientId) {
        // Ajoute l'ID de l'ingrédient à la liste des ingrédients de la recette
        ingredientIds.add(ingredientId);
    }*/
}


