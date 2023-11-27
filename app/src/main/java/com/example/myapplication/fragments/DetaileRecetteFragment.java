package com.example.myapplication.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentDetaileRecetteBinding;
import com.example.myapplication.databinding.FragmentNewRecetteBinding;


public class DetaileRecetteFragment extends Fragment {

    private FragmentDetaileRecetteBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDetaileRecetteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.ibRetourRep.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_detaileRecetteFragment_to_navigation_repertoire);
        });

    }
}