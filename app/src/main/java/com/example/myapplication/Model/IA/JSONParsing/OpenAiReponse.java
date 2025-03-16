package com.example.myapplication.Model.IA.JSONParsing;

import android.os.Message;

import com.google.gson.annotations.SerializedName;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpenAiReponse {
    @SerializedName("choices")
    private Choice[] choices;

    public Choice[] getChoices() {
        return choices;
    }

    public void setChoices(Choice[] choices) {
        this.choices = choices;
    }

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
