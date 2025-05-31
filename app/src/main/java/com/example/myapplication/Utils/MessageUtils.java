package com.example.myapplication.Utils;

import com.example.myapplication.Model.IA.DTO.MessageDTO;
import com.example.myapplication.Model.IA.JSONParsing.OpenAiRequete;

import java.util.ArrayList;
import java.util.List;

public class MessageUtils {

    //nettoyage
    public static String sanitizeMessage(String message) {
        String regex = "^[a-zA-Z0-9\\s]+$";
        if (!message.matches(regex)) {
            message = message.replaceAll("[^a-zA-Z0-9\\s]", "");
        }
        return message;
    }
    //construction requete
    public static OpenAiRequete buildRequeteOpenAI(String userMessage) {
        List<MessageDTO> messagesDTO = new ArrayList<>();
        messagesDTO.add(new MessageDTO("system", "Tu es un assistant spécialisé en cuisine. " +
                "Tu réponds uniquement aux questions liées à la cuisine, aux recettes, aux ingrédients et " +
                "à la préparation des repas. Si une demande ne concerne pas la cuisine, tu dois répondre : " +
                "'Je suis un assistant culinaire et je ne peux répondre qu'à des questions sur la cuisine.'"));
        messagesDTO.add(new MessageDTO("user", userMessage));
        return new OpenAiRequete("gpt-4o-mini", messagesDTO);
    }
    public static int getLoggedInUserId() {
        return 1; // Temporaire
    }
}
