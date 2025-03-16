package com.example.myapplication.Utils.Adapter;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.IA.Message;
import com.example.myapplication.databinding.FragmentRecyclerFavorisIABinding;

public class FavorisViewHolder extends RecyclerView.ViewHolder {
    FragmentRecyclerFavorisIABinding binding;
    TextView tvTitreReqFavIA;
    public FavorisViewHolder(FragmentRecyclerFavorisIABinding binding) {
        super(binding.getRoot());
        this.binding = binding;
        tvTitreReqFavIA = binding.tvTitreReqFavIA;
    }
    public void bind(Message openAiReponse) {
        //tvTitreReqFavIA.setText(OpenAiReponse.getTextAfterKeyword(openAiReponse.getMessage(), "Titre :"));//trouver une solutio pour avoir un titre de réponse

    }
}
