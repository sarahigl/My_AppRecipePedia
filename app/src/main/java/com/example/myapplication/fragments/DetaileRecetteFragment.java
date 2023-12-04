package com.example.myapplication.fragments;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentDetaileRecetteBinding;
import com.example.myapplication.databinding.FragmentNewRecetteBinding;

import java.util.Objects;


public class DetaileRecetteFragment extends Fragment {

    private FragmentDetaileRecetteBinding binding;
    private ImageButton boutonRetour;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDetaileRecetteBinding.inflate(inflater, container, false);
        boutonRetour = binding.ibRetourRepertoire;

        //recetteList();
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        boutonRetour.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_detaileRecetteFragment_to_navigation_repertoire);
        });

    }
}