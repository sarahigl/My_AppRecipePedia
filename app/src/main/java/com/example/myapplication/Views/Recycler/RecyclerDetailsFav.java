package com.example.myapplication.Views.Recycler;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecyclerDetailsFav#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecyclerDetailsFav extends Fragment {

    public RecyclerDetailsFav() {
        // Required empty public constructor
    }

    public static RecyclerDetailsFav newInstance() {

        return new RecyclerDetailsFav();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycler_details_fav, container, false);
    }
}