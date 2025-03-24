package com.example.myapplication.Utils.Adapter;

import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.IA.Message;
import com.example.myapplication.databinding.FragmentRecyclerFavorisIABinding;

public class FavorisViewHolder extends RecyclerView.ViewHolder {
    FragmentRecyclerFavorisIABinding binding;
    TextView titre;
    CardView cardFavIA;
    public FavorisViewHolder(FragmentRecyclerFavorisIABinding binding) {
        super(binding.getRoot());
        this.binding = binding;
        titre = binding.tvTitreReqFavIA;
        cardFavIA = binding.favIACard;
    }
    public void bind(Message msg) {
        msg.setTitle(generateTitleFromMessage(msg.getMessage()));
    }

    //Generation du titre de la recette
    private String generateTitleFromMessage(String msg){
        String cleanedMsg = cleanMessage(msg);
        int maxLength = 12;
        // Tronquez le texte si nécessaire et ajoutez une ellipse
        if (cleanedMsg.length() > maxLength) {
            return cleanedMsg.substring(0, maxLength) + "...";
        }
        return cleanedMsg;
    }

    private String cleanMessage(String msg) {
        String[] keywordsToRemove = {
                "bonjour ! ", "voici", "une", "recette", "simple", "pour", "préparer","liste", "ingrédients", "Bien sûr ! ", "faire", "!", "les", "des", "une", "et",
                "rapide", "idée", "menu", "un", "bon"
        };
        for (String keyword : keywordsToRemove) {
            msg = msg.replaceAll("(?i)\\b" + keyword + "\\b", "").trim();
        }
        // Supprimez les espaces multiples résultants
        msg = msg.replaceAll("\\s+", " ");
        return msg;
    }

}

