package com.example.myapplication.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.ViewModel.RecetteViewModel;
import com.example.myapplication.bean.Recette;
import com.example.myapplication.databinding.FragmentNewRecetteBinding;
import com.example.myapplication.databinding.FragmentRepertoireBinding;
import com.example.myapplication.utils.MyAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RepertoireFragment extends Fragment {

    private FragmentRepertoireBinding binding;
    RecetteViewModel recetteViewModel;
    RecyclerView recyclerView;
    List<Recette> recetteList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRepertoireBinding.inflate(inflater, container,false);

        recetteViewModel = new ViewModelProvider(this).get(RecetteViewModel.class);

        //binding bouton ouverture fragment new recette
        binding.ibNewRecetteAjout.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_repertoire_to_Fragment_nouvelleRecette);
        });

        //Initialisation de RecyclerView et du gestionnaire de disposition
        recyclerView = binding.rVRepertoire; // Obtient la référence au RecyclerView à partir du layout lié
        //getActivity est l'équivalent de class.this
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        //Création boîte de dialogue avec une vue personnalisée
        Activity activity = getActivity();
        if (activity != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setCancelable(false);
            builder.setView(R.layout.progress_layout);
            AlertDialog dialog = builder.create();
            dialog.show();

            //Initialisation de la liste de recettes et connexion à l'adaptateur
            recetteList = new ArrayList<>();
            MyAdapter adapter = new MyAdapter(recetteList, recetteViewModel);
            recyclerView.setAdapter(adapter);

            //Récupération des données depuis Firebase Realtime Database
            databaseReference = FirebaseDatabase.getInstance().getReference("Recette");
            dialog.show();
            eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
                //écouteur d'événements à la référence de la BDD => déclenché chaque fois que les données sous la référence changent
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    /*les données sont lues à partir de snapshot (une représentation instantanée des données dans la base de données)
                    et mises à jour dans la liste recetteList*/
                    recetteList.clear();
                    for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                        Recette recette = itemSnapshot.getValue(Recette.class);
                        if(recette != null){
                            recette.setKey(itemSnapshot.getKey());
                            recetteList.add(recette);
                        }
                    }
                    //Log.d("DEBUG", "Nombre d'éléments dans la liste : " + recetteList.size());
                    adapter.notifyDataSetChanged();

                    if (dialog.isShowing()) { //aider à éviter des erreurs potentielles liées à la libération d'une ressource qui a déjà été libérée
                        dialog.dismiss();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    //si la lecture des données est annulée la boîte de dialogue est simplement fermée
                    dialog.dismiss();
                }
            });
        }

        return binding.getRoot();
    }

    @Override //vide le cache mémoire
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}