package com.example.myapplication.Utils.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.IA.Message;
import com.example.myapplication.R;
import com.example.myapplication.ViewModel.ChatIAViewModel;
import com.example.myapplication.Views.IA.ChatIAFragment;
import com.example.myapplication.Views.IA.FavorisIADetailsFragment;
import com.example.myapplication.databinding.FragmentRecyclerFavorisIABinding;


import java.util.List;

public class AdapterFavorisIA extends RecyclerView.Adapter<FavorisViewHolder> {
    private Context context;
    private ChatIAViewModel chatIAViewModel;

    private List<Message> favorisList;

    public Context getContext() { return context;}
    public void setContext(Context context) { this.context = context;}
    public List<Message> getFavorisList() { return favorisList;}
    public void setFavorisList(List<Message> favorisList) { this.favorisList = favorisList;}

    public AdapterFavorisIA(List<Message> favorisList, ChatIAViewModel chatIAViewModel) {
        this.favorisList = favorisList;
        this.chatIAViewModel = chatIAViewModel;
    }
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
        Message msg = favorisList.get(position);

        holder.bind(msg);
        holder.titre.setText(msg.getTitle());
        holder.cardFavIA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatIAViewModel fragment = new ViewModelProvider((ViewModelStoreOwner) view.getContext()).get(ChatIAViewModel.class);
                fragment.setMessage(msg.getMessage());
                Navigation.findNavController(view).navigate(R.id.action_navigation_home_to_favorisIADetailsFragment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return favorisList.size();
    }

}

