package com.example.myapplication.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.ViewModel.RecetteViewModel;
import com.example.myapplication.bean.Recette;
import com.example.myapplication.databinding.FragmentNewRecetteBinding;
import com.example.myapplication.databinding.FragmentParametreBinding;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class NewRecetteFragment extends Fragment {

    private FragmentNewRecetteBinding binding;
    private RecetteViewModel recetteViewModel;

    Button publicationButton;
    EditText etTitreRecette, etAjoutIngredient, etAjoutDescription, etTempsCuisson;
    ImageView uploadImage;
    String imageURL;
    Uri uri;//Uniform Resource Identifier

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNewRecetteBinding.inflate(inflater, container, false);

        etTitreRecette = binding.etTitreRecette;
        publicationButton = binding.bPublier;
        etAjoutIngredient = binding.etAjoutIngredient;
        etAjoutDescription = binding.etAjoutDescription;
        etTempsCuisson = binding.etTempsCuisson;
        uploadImage = binding.uploadImage;

        recetteViewModel = new ViewModelProvider(requireActivity()).get(RecetteViewModel.class);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            uri = data.getData();
                            uploadImage.setImageURI(uri);
                        } else {
                            Toast.makeText(getActivity(), "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        uploadImage.setOnClickListener(view -> {
            Intent photoPicker = new Intent(Intent.ACTION_PICK);
            photoPicker.setType("image/*");
            activityResultLauncher.launch(photoPicker);
        });
        publicationButton.setOnClickListener(view -> saveData());
        return binding.getRoot();
    }
    /*public void returnRepertory(){
        Fragment repertoireFragment = new RepertoireFragment();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.navigation_repertoire, repertoireFragment);
        fragmentTransaction.commit();
    }*/

    //méthodes d'enregistrement image recette et des données entrée dans la BDD
    public void saveData(){
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Android Images")
                .child(Objects.requireNonNull(uri.getLastPathSegment()));
        //chargement
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                imageURL = urlImage.toString();
                uploadData();
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
            }
        });
    }

    public void uploadData() {
        String titre = etTitreRecette.getText().toString();
        String ingredient = etAjoutIngredient.getText().toString();
        String description = etAjoutDescription.getText().toString();
        String cuisson = etTempsCuisson.getText().toString();

        // Vérifier si les champs ne sont pas vides
        if (!TextUtils.isEmpty(titre) && !TextUtils.isEmpty(ingredient) && !TextUtils.isEmpty(description) && !TextUtils.isEmpty(cuisson)) {
            // Créer une liste d'ingrédients
            List<String> ingredientsList = Arrays.asList(ingredient.split(", ")); // Supposant que les ingrédients sont séparés par des virgules et un espace
            // Créer un objet pour stocker les données
            Recette recette = new Recette(titre, ingredientsList, description, cuisson, imageURL);
            // MaJ  valeurs du ViewModel
            recetteViewModel.setImageURL(imageURL);
            recetteViewModel.setTitre(titre);
            recetteViewModel.setIngredients(ingredientsList);
            recetteViewModel.setDescription(description);
            recetteViewModel.setTempsCuisson(cuisson);

            DatabaseReference recettesRef = FirebaseDatabase.getInstance().getReference("Recette");//une référence à la BDD
            String newRecetteKey = recettesRef.push().getKey();//push() pour générer un identifiant auto-incrémenté

            /*for (String ingredientSeul : ingredientsList) {
                DatabaseReference newIngredientRef = FirebaseDatabase.getInstance().getReference("Ingredient"); // Crée une nouvelle référence pour l'ingrédient
                String ingredientId = newIngredientRef.push().getKey();// Récupère l'ID de l'ingrédient nouvellement créé

                // Crée un objet Ingredient et l'ajoute à la table des ingrédients
                Ingredient newIngredient = new Ingredient(ingredientId, ingredient);
                newIngredientRef.setValue(newIngredient);

                // Associe l'ID de l'ingrédient à la recette dans la table des recettes
                recetteViewModel.addIngredientId(ingredientId);
            }*/


            // Enregistrez les données de la recette à l'emplacement avec le nouvel identifiant
            if (newRecetteKey != null) {
                recettesRef.child(newRecetteKey)
                        .setValue(recette)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getActivity(), "Recette enregistrée", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        } else {
            // Afficher un message d'erreur si un champ est vide
            Toast.makeText(getActivity(), "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //binding bouton retour repertoire
        binding.ibRetourNewRecette.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_Fragment_nouvelleRecette_to_navigation_repertoire);
        });
    }

    @Override //vide le cache mémoire
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
// pour respecter mon MLD
/*
for (String ingredient : ingredientsList) {
        DatabaseReference newIngredientRef = ingredientsRef.push(); // Crée une nouvelle référence pour l'ingrédient
        String ingredientId = newIngredientRef.getKey(); // Récupère l'ID de l'ingrédient nouvellement créé

        // Crée un objet Ingredient et l'ajoute à la table des ingrédients
        Ingredient newIngredient = new Ingredient(ingredientId, ingredient);
        newIngredientRef.setValue(newIngredient);

        // Associe l'ID de l'ingrédient à la recette dans la table des recettes
        recetteViewModel.addIngredientId(ingredientId);
        }

public void addIngredientId(String ingredientId) {
    // Ajoute l'ID de l'ingrédient à la liste des ingrédients de la recette
    ingredientIds.add(ingredientId);
}
*/
