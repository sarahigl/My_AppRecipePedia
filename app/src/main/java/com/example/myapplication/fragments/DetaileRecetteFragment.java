package com.example.myapplication.fragments;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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



public class DetaileRecetteFragment extends Fragment {

    private FragmentDetaileRecetteBinding binding;
    private RecetteViewModel recetteViewModel;
    private ImageButton boutonRetour;
    TextView tvTitreDRecette,tvIngredientRecette, tvDescriptionRecette, tvTempsCuissonRecette;
    ImageView imageURL;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("DEBUG", "DetailFragment onCreateView");
        // Inflate the layout for this fragment
        binding = FragmentDetaileRecetteBinding.inflate(inflater, container, false);
        recetteViewModel = new ViewModelProvider(requireActivity()).get(RecetteViewModel.class);
        boutonRetour = binding.ibRetourRepertoire;
        imageURL = binding.imageDRecette;

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Log.d("DEBUG", "DetailFragment onViewCreated");
        // Observer pour l'URL de l'image
        recetteViewModel.getImageURL().observe(getViewLifecycleOwner(), newImage -> {
           if (newImage != null) {
               Glide.with(requireContext()).load(newImage).into(imageURL);
            }
        });
        // Observer pour le titre
        recetteViewModel.getTitre().observe(getViewLifecycleOwner(), newTitre -> {
            //Log.d("DEBUG", "Observer for titre triggered" + newTitre);
            if (newTitre != null) {
                binding.tvTitreDRecette.setText(newTitre);
            }
        });
        // Observer pour le ingredient et affichage avec retour à la ligne
        recetteViewModel.getIngredients().observe(getViewLifecycleOwner(), newIngredients -> {
            if (newIngredients != null) {
                StringBuilder formattedIngredients = new StringBuilder();
                for (String ingredient : newIngredients) {
                    formattedIngredients.append(ingredient).append("\n");
                }
                binding.tvIngredientRecette.setText(formattedIngredients.toString());
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

        boutonRetour.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_detaileRecetteFragment_to_navigation_repertoire);
        });

    }
    @Override //vide le cache mémoire
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}