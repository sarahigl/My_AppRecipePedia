package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.MenuItem;


import com.example.myapplication.ViewModel.RecetteViewModel;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.FirebaseApp;


public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    ActivityMainBinding binding;
    private NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        RecetteViewModel recetteViewModel = new ViewModelProvider(this).get(RecetteViewModel.class);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        } else {
            throw new IllegalStateException("NavHostFragment is null");
        }
        FirebaseApp.initializeApp(this);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.navigation_actu) {
            navController.navigate(R.id.navigation_home);
            return true;
        } else if (item.getItemId() == R.id.navigation_rep) {
            navController.navigate(R.id.navigation_rep);
            return true;
        } else if (item.getItemId() == R.id.navigation_profil) {
            navController.navigate(R.id.profilFragment);
            return true;
        }
        return false;
    }
}