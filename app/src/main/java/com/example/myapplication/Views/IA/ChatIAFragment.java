package com.example.myapplication.Views.IA;

import android.annotation.SuppressLint;
import android.icu.text.DateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Data.Local.MyDatabaseHelper;
import com.example.myapplication.Model.IA.DTO.MessageDTO;
import com.example.myapplication.Model.IA.JSONParsing.OpenAiReponse;
import com.example.myapplication.Model.IA.JSONParsing.OpenAiRequete;
import com.example.myapplication.Model.IA.Message;
import com.example.myapplication.Utils.API.ApiServiceIA;
import com.example.myapplication.Utils.Adapter.AdapterChatMessage;
import com.example.myapplication.Utils.MessageUtils;
import com.example.myapplication.Utils.RetrofitClient;
import com.example.myapplication.ViewModel.ChatIAViewModel;
import com.example.myapplication.databinding.FragmentChatIABinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatIAFragment extends Fragment implements AdapterChatMessage.OnFavClickListener{
    private FragmentChatIABinding binding;
    private AdapterChatMessage chatMessageAdapter;
    private ChatIAViewModel requeteIAViewModel;
    private final List<Message> chatMessages = new ArrayList<>();
    private ApiServiceIA apiServiceIA;
    RecyclerView recyclerView;
    EditText inputchat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChatIABinding.inflate(inflater, container, false);
        inputchat = binding.inputchat;
        requeteIAViewModel = new ViewModelProvider(requireActivity()).get(ChatIAViewModel.class);
        //Initialisation de RecyclerView et du gestionnaire de disposition
        // Obtient la référence au RecyclerView à partir du layout lié
        recyclerView= binding.rvchatUser;
        LinearLayoutManager linearLayoutManagerUser = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManagerUser);
        apiServiceIA = RetrofitClient.getApiServiceIA(); //api service
        chatMessageAdapter= new AdapterChatMessage(chatMessages);//initilisation de l'adapter
        //établie la communication entre l'adapter et le recyclerview (délégue la gestion de l'event)
        chatMessageAdapter.setOnFavClickListener(this);
        recyclerView.setAdapter(chatMessageAdapter);
        //send function
        binding.sendmessage.setOnClickListener(this::sendMessage);
        return binding.getRoot();
    }
    ///////////////////////FAVORISATION////////////////////////////////////////////
    public void addFavoris(String msg){
        String date = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        int idUtilisateur = MessageUtils.getLoggedInUserId();
        MyDatabaseHelper databaseHelper = new MyDatabaseHelper(getContext());
        long rowId = databaseHelper.insertMessageData(msg, date,0, idUtilisateur);
        if(rowId == -1){
            Toast.makeText(getContext(), "Erreur lors de l'ajout des favoris", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getContext(), "Ajout des favoris avec succès", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onFavClick(String message) {
        Log.d("ChatIAFragment", "onFavClick: " + message);
        addFavoris(message);
    }
    ///////////////////////FAVORISATION FIN////////////////////////////////////////////
    public void makeApiCall(OpenAiRequete openAiRequete){
        apiServiceIA.createRequest(openAiRequete).enqueue(new Callback<OpenAiReponse>() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onResponse(Call<OpenAiReponse> call, Response<OpenAiReponse> response) {
                //Log.d("ChatIAFragment", "API call onResponse");
                if(response.isSuccessful() && response.body() != null){
                    OpenAiReponse reponse = response.body();
                    String botMessage = reponse.getMessage();
                    // Sanitize le message IA pour ne garder que les caractères alphanumériques
                    botMessage = botMessage.replaceAll("[^a-zA-Z0-9\\s.,?!'()àâäéèêëîïôöùûüçœŒ-]", "");
                    botMessage = botMessage.trim();
                    //Log.d("D/ChatIAFragment", "ReponseIA : " + reponse.getMessage());
                    chatMessageAdapter.addMessage(new Message(botMessage, Message.TYPE_BOT));
                } else {
                    //Log.e("ChatIAFragment", "API call failed with code: " + response.code());
                    switch (response.code()) {
                        case 401 :
                            Toast.makeText(getActivity(), "Erreur d'authentification. " +
                                    "Veuillez vérifier que vous avez bien renseigné votre clé API",
                                    Toast.LENGTH_SHORT).show();
                            break;
                        case 403:
                            Toast.makeText(getContext(), "Vous avez peut-être dépassé la limite de requêtes.",
                                    Toast.LENGTH_LONG).show();
                            break;
                        case 500:
                            Toast.makeText(getContext(), "Erreur interne du serveur. Veuillez réessayer plus tard.",
                                    Toast.LENGTH_LONG).show();
                            break;
                        default:
                            Toast.makeText(getContext(), "Une erreur est survenue. Veuillez réessayer plus tard.",
                                    Toast.LENGTH_LONG).show();
                            break;
                    }
                }
            }
            @Override
            public void onFailure(Call<OpenAiReponse> call, Throwable t) {
                //Log.e("E/ChatIAFragment", "API call failed", t);
                Toast.makeText(getContext(), "Erreur de connexion. Veuillez vérifier votre connexion Internet ou réessayer plus tard.", Toast.LENGTH_LONG).show();
                binding.inputchat.setText("");
                //affichage d'un message d'erreur dans la discussion
                chatMessageAdapter.addMessage(new Message("Erreur de connexion", Message.TYPE_BOT));
            }
        });

    }
    public void sendMessage(View view) {
        String message = Objects.requireNonNull(binding.inputchat.getText()).toString().trim();
        //empêche les messages vides
        if (message.length() < 2) {
            binding.inputchat.setError("Veuillez saisir un message de plus de 2 caractères");
            return;
        }
        //empêche la surchage
        if(message.length()>500){
            binding.inputchat.setError("Message trop long (max 500 caractères)");
            return;
        }
        // regex pour ne garder que les caractères autorisés
        message = MessageUtils.sanitizeMessage(message);
        chatMessageAdapter.addMessage(new Message(message, Message.TYPE_USER));
        binding.inputchat.setText("");
        //creation objet requeteOpenIA qui sera envoyé à openAI api
        OpenAiRequete openAiRequete = MessageUtils.buildRequeteOpenAI(message);
        //envoyer la requete à l'API
        makeApiCall(openAiRequete);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}