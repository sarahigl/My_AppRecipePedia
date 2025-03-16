package com.example.myapplication.Views.IA;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Data.Local.MyDatabaseHelper;
import com.example.myapplication.Model.IA.Message;
import com.example.myapplication.Utils.Adapter.AdapterFavorisIA;
import com.example.myapplication.databinding.FragmentFavorisIABinding;

import java.util.List;

public class FavorisIAFragment extends Fragment {
    private FragmentFavorisIABinding binding;
    RecyclerView recyclerView;
    List<Message> favoris;
    private AdapterFavorisIA adapter;
    MyDatabaseHelper databaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentFavorisIABinding.inflate(inflater, container, false);
        recyclerView = binding.rvFavIA;
        databaseHelper = new MyDatabaseHelper(getContext());
        favoris = databaseHelper.getUserFavorites(1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterFavorisIA(favoris);
        recyclerView.setAdapter(adapter);

        // Inflate the layout for this fragment
        return binding.getRoot();
    }
    @Override //vide le cache mémoire
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}