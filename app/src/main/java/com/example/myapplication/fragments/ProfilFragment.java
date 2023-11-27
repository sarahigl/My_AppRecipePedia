package com.example.myapplication.fragments;
import static androidx.navigation.fragment.FragmentKt.findNavController;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentProfilBinding;


public class ProfilFragment extends Fragment {
    //Déclaration du binding nullable
    FragmentProfilBinding binding;
    private NavController navController;


    //Et la version non nullable pour simplifier le reste du code
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        binding = FragmentProfilBinding.inflate(inflater, container,false);

        //fait cracher l'app complete ne s'ouvre pas?!!! compatibilité??
        //navController = Navigation.findNavController(getView());


        return binding.getRoot();


    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.ibParametre.setOnClickListener(v -> {
            navController.navigate(R.id.action_profilFragment_to_parametreFragment2);
        });

        binding.ibFavoris.setOnClickListener(v -> {
            navController.navigate(R.id.action_profilFragment_to_favorisfragment);
        });
    }

    @Override //vide le cache mémoire
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }



}