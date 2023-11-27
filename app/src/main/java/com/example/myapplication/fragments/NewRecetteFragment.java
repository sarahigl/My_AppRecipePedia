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
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;
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
import java.util.Calendar;
import java.util.Objects;

public class NewRecetteFragment extends Fragment {

    private FragmentNewRecetteBinding binding;
    Button publicationButton;
    EditText etTitreRecette, etAjoutIngredient, etAjoutDescription, etTempsCuisson;
    ImageView uploadImage;
    String imageURL;
    //Uniform Resource Identifier
    Uri uri;


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

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            assert data != null;
                            uri = data.getData();
                            uploadImage.setImageURI(uri);
                        } else {
                            Toast.makeText(getActivity(), "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });
        publicationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.ibRetourNewRecette.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_Fragment_nouvelleRecette_to_navigation_repertoire);
        });
    }
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
                while (!uriTask.isComplete()) {
                    ;
                }
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
        if (etTitreRecette != null && etAjoutIngredient != null && etAjoutDescription != null && etTempsCuisson != null) {
            // Créer un objet pour stocker les données
            Recette recette = new Recette(
                    etTitreRecette.getText().toString(),
                    etAjoutIngredient.getText().toString(),
                    etAjoutDescription.getText().toString(),
                    etTempsCuisson.getText().toString()
            );

            // Obtenir une référence à la base de données
            DatabaseReference recettesRef = FirebaseDatabase.getInstance().getReference("Recette");

            // Utiliser push() pour générer un identifiant auto-incrémenté
            String newRecetteKey = recettesRef.push().getKey();

            // Enregistrez les données de la recette à l'emplacement avec le nouvel identifiant
            recettesRef.child(newRecetteKey)
                    .setValue(recette)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getActivity(), "Recette enregistrée avec succès", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }



    @Override //vide le cache mémoire
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
