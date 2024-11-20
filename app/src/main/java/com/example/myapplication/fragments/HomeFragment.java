package com.example.myapplication.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentHomeBinding;
public class HomeFragment extends Fragment {

private FragmentHomeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.toggleButton.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (isChecked) {
                if (checkedId == R.id.chat_ia) {
                    handleChatIASelection();
                } else if (checkedId == R.id.favoris_ia) {
                    handleFavorisIASelection();
                }
            }
        });
        binding.toggleButton.check(R.id.chat_ia);

        return binding.getRoot();
    }
    private void handleChatIASelection() {

    }

    private void handleFavorisIASelection() {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}