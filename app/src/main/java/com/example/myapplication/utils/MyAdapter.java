package com.example.myapplication.utils;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.myapplication.bean.Recette;
import com.example.myapplication.databinding.FragmentDetaileRecetteBinding;
import com.example.myapplication.databinding.RecyclerRecetteRepertoireBinding;
import com.example.myapplication.fragments.DetaileRecetteFragment;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<Recette>recetteList;

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

    public MyAdapter() {

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_recette_repertoire, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(recetteList.get(position).getImageURL()).into(holder.imageRec);
        holder.tvTitreRec.setText(recetteList.get(position).getTitre());

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Créer une instance du fragment
                DetaileRecetteFragment fragment = new DetaileRecetteFragment();

                // Passer des données au fragment s'il y en a
                Bundle bundle = new Bundle();
                bundle.putString("Image", recetteList.get(holder.getAdapterPosition()).getImageURL());
                bundle.putString("Titre", recetteList.get(holder.getAdapterPosition()).getTitre());
                fragment.setArguments(bundle);

                // Use the fragment to get the FragmentManager
                FragmentManager fragmentManager = fragment.getParentFragmentManager(); //getParentFragmentManager() => remplace getFragmentManager car deprecated
                fragmentManager.beginTransaction()
                        .replace(R.id.detaileRecetteFragment, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return recetteList.size();
    }
}
class MyViewHolder extends RecyclerView.ViewHolder {
    private RecyclerRecetteRepertoireBinding binding;
    ImageView imageRec;
    TextView tvTitreRec;
    CardView recCard;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageRec = itemView.findViewById(R.id.imageRec);
        tvTitreRec = itemView.findViewById(R.id.tvTitreRec);
        recCard = itemView.findViewById(R.id.recCard);
    }
}
