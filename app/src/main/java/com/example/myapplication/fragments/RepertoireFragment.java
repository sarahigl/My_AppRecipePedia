package com.example.myapplication.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.bean.Recette;
import com.example.myapplication.databinding.FragmentNewRecetteBinding;
import com.example.myapplication.databinding.FragmentRepertoireBinding;
import com.example.myapplication.utils.MyAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RepertoireFragment extends Fragment {

    private FragmentRepertoireBinding binding;
    RecyclerView recyclerView;
    List<Recette> recetteList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRepertoireBinding.inflate(inflater, container,false);

        binding.ibNewRecetteAjout.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_repertoire_to_Fragment_nouvelleRecette);
        });

        recyclerView = binding.rVRepertoire; //getActivity est l'équivalent de class.this
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setLayoutManager(gridLayoutManager);
        Activity activity = getActivity();
        if (activity != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setCancelable(false);
            builder.setView(R.layout.progress_layout);
            AlertDialog dialog = builder.create();
            dialog.show();


            recetteList = new ArrayList<>();

            //connection avec l'adapteur
            MyAdapter adapter = new MyAdapter(((AppCompatActivity) getActivity()).getSupportFragmentManager(), recetteList);
            recyclerView.setAdapter(adapter);

            databaseReference = FirebaseDatabase.getInstance().getReference("Recettes");
            dialog.show();
            eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    recetteList.clear();
                    for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                        Recette recette = itemSnapshot.getValue(Recette.class);
                        assert recette != null;
                        recette.setKey(itemSnapshot.getKey());
                        recetteList.add(recette);
                    }
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    dialog.dismiss();
                }
            });
        }

        return binding.getRoot();
    }

    @Override //vide le cache mémoire
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}