package com.example.myapplication.Utils;

import com.example.myapplication.BuildConfig;
import com.example.myapplication.Model.RequeteIA;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Properties;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UtilsApiAI {
    public void asynchronousCallAPI(String url, RequeteIA requeteIA){
        final OkHttpClient client = new OkHttpClient();
        //creation d'un objet json pour le body de la requete
        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("model", requeteIA.getModel());
        //creation array de message
        com.google.gson.JsonArray messagesArray = new com.google.gson.JsonArray();
        //ajouter le msg pour l'asssistant IA
        JsonObject systemMessage = new JsonObject();
        systemMessage.addProperty("role", "system");
        systemMessage.addProperty("content", "You are an expert in AI.");
        messagesArray.add(systemMessage);
        //ajouter le msg de l'utilisateur
        JsonObject userMessage = new JsonObject();
        userMessage.addProperty("role", "user");
        userMessage.addProperty("content", requeteIA.getCorpRequete());
        messagesArray.add(userMessage);
        jsonBody.add("messages", messagesArray);
        //conversion json => string
        String jsonString = jsonBody.toString();
        //creation du body de la requete
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(jsonString, JSON);
        //construction de la req
        final Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer " + BuildConfig.OPENAI_API_KEY)
                .url(url)
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                System.out.println(response.body().string());
            } else {
                System.out.println("Erreur : " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
