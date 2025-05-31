package com.example.myapplication.Model.IA.JSONParsing;

import android.os.Message;

import com.google.gson.annotations.SerializedName;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpenAiReponse {

    // L'annotation "@SerializedName("choices")" est utilisée par la bibliothèque de parsing JSON (comme Gson ou Moshi).
    // Elle indique que lorsque Retrofit (qui utilise cette bibliothèque) reçoit une réponse JSON,
    // il doit chercher un champ nommé "choices" dans le JSON et mapper sa valeur à cette variable "choices".
    @SerializedName("choices")
    private Choice[] choices;

    public Choice[] getChoices() {
        return choices;
    }

    public void setChoices(Choice[] choices) {
        this.choices = choices;
    }
    // méthode utilitaire :
    // Son but est de récupérer le contenu du message de l'IA de manière simple.
    // Elle retourne une chaîne de caractères (String).
    public String getMessage() {
        if (choices != null && choices.length > 0) {
            return choices[0].getMessage().getContent();
        }
        return null;
    }

    public static class Choice {
        @SerializedName("message")
        private Message message;

        public Message getMessage() {
            return message;
        }

        public void setMessage(Message message) {
            this.message = message;
        }
    }

    public static class Message {
        @SerializedName("content")
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
    public static String getTextAfterKeyword(String response, String keyword) {
        String regex = "(?i)" + keyword + "\\s+(.+)";  // Récupère tout après le mot-clé
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(response);

        if (matcher.find()) {
            return matcher.group(1).trim();  // Retourne le texte après le mot-clé
        }
        return "Aucune correspondance trouvée";
    }
}
