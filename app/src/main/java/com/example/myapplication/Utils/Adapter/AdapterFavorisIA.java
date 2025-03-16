package com.example.myapplication.Utils.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.IA.Message;
import com.example.myapplication.databinding.FragmentRecyclerFavorisIABinding;


import java.util.List;

public class AdapterFavorisIA extends RecyclerView.Adapter<FavorisViewHolder> {
    private Context context;

    private List<Message> favorisList;

    public Context getContext() { return context;}
    public void setContext(Context context) { this.context = context;}
    public List<Message> getFavorisList() { return favorisList;}
    public void setFavorisList(List<Message> favorisList) { this.favorisList = favorisList;}

    public AdapterFavorisIA(List<Message> favorisList) {
        this.favorisList = favorisList;
    }
    public AdapterFavorisIA() {}

    @NonNull
    @Override
    public FavorisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FragmentRecyclerFavorisIABinding binding = FragmentRecyclerFavorisIABinding.inflate(LayoutInflater.from(parent.getContext()));
        return new FavorisViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FavorisViewHolder holder, int position) {
        Message reponse = favorisList.get(position);
        holder.bind(reponse);

    }

    @Override
    public int getItemCount() {
        return favorisList.size();
    }
}
