package com.example.myapplication.utils;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.ViewModel.RecetteViewModel;
import com.example.myapplication.bean.Recette;
import com.example.myapplication.databinding.FragmentDetaileRecetteBinding;
import com.example.myapplication.databinding.RecyclerRecetteRepertoireBinding;
import com.example.myapplication.fragments.DetaileRecetteFragment;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<Recette>recetteList;
    private RecetteViewModel recetteViewModel;
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Recette> getRecetteList() {
        return recetteList;
    }

    public void setRecetteList(List<Recette> recetteList) {
        this.recetteList = recetteList;
    }

    public MyAdapter(FragmentManager fragmentManager, List<Recette> recetteList) {
        this.recetteList = recetteList;
    }

    public MyAdapter(List<Recette> recetteList, RecetteViewModel recetteViewModel) {
        this.recetteList = recetteList;
        this.recetteViewModel = recetteViewModel;
    }

    public MyAdapter() {

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//instanciation de la ligne
        RecyclerRecetteRepertoireBinding binding = RecyclerRecetteRepertoireBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new MyViewHolder(binding);
    }

    //récuperation de l'item
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //Log.d("DEBUG", "Position : " + position);
        //ajouter un if -
        String imageUrl = recetteList.get(position).getImageURL();
        if(imageUrl != null && !imageUrl.isEmpty()){
            Glide.with(holder.itemView.getContext()).load(recetteList.get(position).getImageURL()).dontAnimate().into(holder.imageRec);
        }else{
            Log.d("DEBUG", " no image ");
            //mettre message d'erreur pour l'utilisateur
        }
        holder.binding.tvTitreRec.setText(recetteList.get(position).getTitre());


        /*CardView est cliqué, un fragment (DetaileRecetteFragment) est créé, des données sont passées à ce fragment,
        et une transaction de fragment est effectuée à l'aide du FragmentManager.*/
        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recetteViewModel.setImageURL(recetteList.get(holder.getAdapterPosition()).getImageURL());
                recetteViewModel.setTitre(recetteList.get(holder.getAdapterPosition()).getTitre());
                recetteViewModel.setIngredient(recetteList.get(holder.getAdapterPosition()).getIngredient());
                recetteViewModel.setDescription(recetteList.get(holder.getAdapterPosition()).getDescription());
                recetteViewModel.setTempsCuisson(recetteList.get(holder.getAdapterPosition()).getTempsCuisson());
            }
        });
    }

    @Override
    public int getItemCount() { //total d'éléments dans la liste (recetteList), indiquant ainsi combien d'éléments doivent être affichés dans le RecyclerView
        return recetteList.size();
    }

}
class MyViewHolder extends RecyclerView.ViewHolder {
    RecyclerRecetteRepertoireBinding binding;
    ImageView imageRec;
    CardView recCard;

    public MyViewHolder(RecyclerRecetteRepertoireBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
        imageRec = binding.imageRec;
        recCard = binding.recCard;
    }

}
