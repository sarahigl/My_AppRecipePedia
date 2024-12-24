package com.example.myapplication.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecyclerFavorisIA#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecyclerFavorisIA extends Fragment {

    public RecyclerFavorisIA() {
        // Required empty public constructor
    }


    public static RecyclerFavorisIA newInstance() {
        RecyclerFavorisIA fragment = new RecyclerFavorisIA();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycler_favoris_i_a, container, false);
    }
}