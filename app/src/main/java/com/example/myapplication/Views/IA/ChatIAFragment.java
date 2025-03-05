package com.example.myapplication.Views.IA;

import static androidx.fragment.app.FragmentManager.TAG;

import android.annotation.SuppressLint;
import android.icu.text.DateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.myapplication.Data.Repositories.ReponseIARepository;
import com.example.myapplication.Model.DTO.Message;
import com.example.myapplication.Model.JSONParsing.OpenAiReponse;
import com.example.myapplication.Model.JSONParsing.OpenAiRequete;
import com.example.myapplication.Model.ReponseIA;
import com.example.myapplication.Model.RequeteIA;
import com.example.myapplication.Utils.API.ApiServiceIA;
import com.example.myapplication.Utils.Adapter.AdapterChatMessage;
import com.example.myapplication.Utils.RetrofitClient;
import com.example.myapplication.ViewModel.RequeteIAViewModel;
import com.example.myapplication.databinding.FragmentChatIABinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatIAFragment extends Fragment {

    private FragmentChatIABinding binding;
    private AdapterChatMessage chatMessageAdapter;
    private RequeteIAViewModel requeteIAViewModel;
    private final List<String> chatMessages = new ArrayList<>();
    private ApiServiceIA apiServiceIA;
    RecyclerView recyclerView;
    EditText inputchat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_chat_i_a, container, false);
        Log.d("ChatIAFragment", "onCreateView called");
        binding = FragmentChatIABinding.inflate(inflater, container, false);
        inputchat = binding.inputchat;
        requeteIAViewModel = new ViewModelProvider(requireActivity()).get(RequeteIAViewModel.class);

        //Initialisation de RecyclerView et du gestionnaire de disposition
        // Obtient la référence au RecyclerView à partir du layout lié
        recyclerView = binding.rvchatUser;
        //getActivity est l'équivalent de class.this
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        //api service
        apiServiceIA = RetrofitClient.getApiServiceIA();

        // Initialize the adapter
        chatMessageAdapter = new AdapterChatMessage(chatMessages);
        recyclerView.setAdapter(chatMessageAdapter);
        binding.sendmessage.setOnClickListener(this::sendMessage);


        return binding.getRoot();
    }

    public void sendMessage(View view) {
        String date = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        String message = Objects.requireNonNull(binding.inputchat.getText()).toString().trim();
        Boolean status = false;
        //teporary solution to modifify later with id loggged user
        int idUtilisateur = getLoggedInUserId();


        if (message.length() >= 2 && !TextUtils.isEmpty(date)) {
            chatMessageAdapter.addMessage(message);
            binding.inputchat.setText("");

            //creation objet requeteOpenIA qui sera envoyé à openAI api
            List<Message> messages = new ArrayList<>();
            messages.add(new Message("system", "You are an expert in cooking."));
            messages.add(new Message("user", message));
            OpenAiRequete openAiRequete = new OpenAiRequete("gpt-4o-mini", messages);

            //API call
            Log.d("ChatIAFragment", "Making API call...");
            apiServiceIA.createRequest(openAiRequete).enqueue(new Callback<OpenAiReponse>() {
                @SuppressLint("RestrictedApi")
                @Override
                public void onResponse(Call<OpenAiReponse> call, Response<OpenAiReponse> response) {
                    Log.d("ChatIAFragment", "API call onResponse");
                    if(response.isSuccessful() && response.body() != null){
                        OpenAiReponse reponse = response.body();
                        Log.d("D/ChatIAFragment", "ReponseIA : " + reponse.getMessage());
                    } else {
                        Log.e("ChatIAFragment", "API call failed with code: " + response.code());
                    }
                }
                @Override
                public void onFailure(Call<OpenAiReponse> call, Throwable t) {
                    Log.e("E/ChatIAFragment", "API call failed", t);
                }

            });
        }else{
            binding.inputchat.setError("Veuillez saisir un message");
        }

    }
    private int getLoggedInUserId() {
        return 1; // Temporaire
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}