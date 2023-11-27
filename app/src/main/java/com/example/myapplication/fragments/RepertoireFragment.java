package com.example.myapplication.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentNewRecetteBinding;
import com.example.myapplication.databinding.FragmentRepertoireBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RepertoireFragment extends Fragment {

    private FragmentRepertoireBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRepertoireBinding.inflate(inflater, container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //navigation vers page de Recette detail
        binding.contentRectangleR.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_repertoire_to_detaileRecetteFragment);
        });
        //navigation vers page de new recette
        binding.ibNewRecetteAjout.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_repertoire_to_Fragment_nouvelleRecette);
        });
    }
    @Override //vide le cache mémoire
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}