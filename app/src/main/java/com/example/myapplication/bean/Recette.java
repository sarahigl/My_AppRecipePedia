package com.example.myapplication.bean;

public class Recette {
    private String key;
    private String titre;
    private String ingredient;
    private String description;
    private String tempsCuisson;
    private String imageURL;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTempsCuisson() {
        return tempsCuisson;
    }

    public void setTempsCuisson(String tempsCuisson) {
        this.tempsCuisson = tempsCuisson;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Recette(String titre, String ingredient, String description, String tempsCuisson, String imageURL) {
        this.titre = titre;
        this.ingredient = ingredient;
        this.description = description;
        this.tempsCuisson = tempsCuisson;
        this.imageURL = imageURL;
    }

    public Recette(String titre, String ingredient, String description, String tempsCuisson) {
        this.titre = titre;
        this.ingredient = ingredient;
        this.description = description;
        this.tempsCuisson = tempsCuisson;
    }

    public Recette() {}

}
