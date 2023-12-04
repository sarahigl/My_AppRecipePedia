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
    TextView tvTitreDRecette, tvDescriptionRecette, tvIngredientRecette;
    ImageView imageDRecette;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDetaileRecetteBinding.inflate(inflater, container, false);
        boutonRetour = binding.ibRetourRepertoire;

        tvTitreDRecette = binding.tvTitreDRecette;
        tvIngredientRecette = binding.tvDescriptionRecette;
        tvDescriptionRecette = binding.tvDescriptionRecette;
        imageDRecette = binding.imageDRecette;

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
   /* public void recetteList(){
        //Intent intent = requireActivity().getIntent();
        Bundle bundle = getArguments();
        if (bundle != null) {
            //Bundle bundle = intent.getExtras();
            //assert bundle != null;
            tvDescriptionRecette.setText(bundle.getString("Description"));
            tvTitreDRecette.setText(bundle.getString("Titre"));
            tvIngredientRecette.setText(bundle.getString("Ingrédients"));
            Glide.with(this).load(bundle.getString("Image")).into(imageDRecette);

        }
    }*/
}