package com.example.myapplication.Views.IA;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication.ViewModel.ChatIAViewModel;
import com.example.myapplication.databinding.FragmentFavorisIADetailsBinding;

public class FavorisIADetailsFragment extends Fragment {

    private ChatIAViewModel chatIAViewModel;
    private FragmentFavorisIADetailsBinding binding;
    TextView tvContentMsg;
    private ImageButton btnRetour;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavorisIADetailsBinding.inflate(inflater, container, false);
        chatIAViewModel = new ViewModelProvider(getActivity()).get(ChatIAViewModel.class);
        btnRetour = binding.ivReturnFav;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
            chatIAViewModel.getMessage().observe(getViewLifecycleOwner(), newMessage -> {
                if (newMessage != null) {
                    binding.tvMsg.setText(newMessage);
                }
            });
            btnRetour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(view).navigateUp();
                }
            });
    }


    @Override //vide le cache mémoire
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}