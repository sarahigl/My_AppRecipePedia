package com.example.myapplication.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentHomeBinding;
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private Fragment chatIAFragment;
    private Fragment favorisIAFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialisation des fragments dans onCreate
        chatIAFragment = new ChatIAFragment();
        favorisIAFragment = new FavorisIAFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Charger le fragment par défaut
        if (savedInstanceState == null) {
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, chatIAFragment)
                    .commit();

            // Sélectionner le bouton par défaut
            binding.toggleButton.check(R.id.chat_ia);
        }

        // Configuration de l'écouteur de boutons
        binding.toggleButton.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (isChecked) {
                handleUserBtnSelected(checkedId);
            }
        });
    }

    private void handleUserBtnSelected(int checkedId) {
        Fragment fragmentToShow = null;

        if (checkedId == R.id.chat_ia) {
            fragmentToShow = chatIAFragment;
        } else if (checkedId == R.id.favoris_ia) {
            fragmentToShow = favorisIAFragment;
        }

        if (fragmentToShow != null) {
            try {
                getChildFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, fragmentToShow)
                        .commit();
            } catch (Exception e) {
                Log.e("HomeFragment", "Error switching fragments", e);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}