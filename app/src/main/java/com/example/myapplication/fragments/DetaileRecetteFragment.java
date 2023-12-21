package com.example.myapplication.fragments;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.ViewModel.RecetteViewModel;
import com.example.myapplication.databinding.FragmentDetaileRecetteBinding;
import com.example.myapplication.databinding.FragmentNewRecetteBinding;

import java.util.Objects;


public class DetaileRecetteFragment extends Fragment {

    private FragmentDetaileRecetteBinding binding;
    private RecetteViewModel recetteViewModel;
    private ImageButton boutonRetour;
    TextView tvTitreDRecette,tvIngredientRecette, tvDescriptionRecette, tvTempsCuissonRecette;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("DEBUG", "DetailFragment onCreateView");
        // Inflate the layout for this fragment
        binding = FragmentDetaileRecetteBinding.inflate(inflater, container, false);
        recetteViewModel = new ViewModelProvider(requireActivity()).get(RecetteViewModel.class);
        boutonRetour = binding.ibRetourRepertoire;



        // Observer pour le titre
        recetteViewModel.getTitre().observe(getViewLifecycleOwner(), newTitre -> {
            Log.d("DEBUG", "Observer for titre triggered" + newTitre);
            if (newTitre != null) {
                binding.tvTitreDRecette.setText(newTitre);
            }
        });
        // Observer pour le ingredient
        recetteViewModel.getIngredient().observe(getViewLifecycleOwner(), newIngredient -> {
            if (newIngredient != null) {
                binding.tvIngredientRecette.setText(newIngredient);
            }
        });
        // Observer pour le description
        recetteViewModel.getDescription().observe(getViewLifecycleOwner(), newDescription -> {
            if(newDescription != null){
                binding.tvDescriptionRecette.setText(newDescription);
            }
        });
        // Observer pour le temps de cuisson
        recetteViewModel.getTempsCuisson().observe(getViewLifecycleOwner(), newCuisson -> {
            if(newCuisson != null){
                binding.tvTempsCuissonRecette.setText(newCuisson);
            }
        });

        return binding.getRoot();
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("DEBUG", "DetailFragment onViewCreated");
        boutonRetour.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_detaileRecetteFragment_to_navigation_repertoire);
        });
        // Observer pour l'URL de l'image
        /*
        recetteViewModel.getImageURL().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String newImageURL) {
                // Mettez à jour votre interface utilisateur en fonction de la nouvelle URL de l'image
                // Par exemple, si vous avez une ImageView nommée "imageView", vous pouvez faire quelque chose comme :
                // Glide.with(requireContext()).load(newImageURL).into(imageView);
            }
        });*/
    }
    @Override //vide le cache mémoire
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}