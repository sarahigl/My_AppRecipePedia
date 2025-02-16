package com.example.myapplication.Views.IA;

import android.icu.text.DateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.myapplication.BuildConfig;
import com.example.myapplication.Data.Repositories.ReponseIARepository;
import com.example.myapplication.Model.RequeteIA;
import com.example.myapplication.Utils.AdapterChatMessage;
import com.example.myapplication.Utils.UtilsApiAI;
import com.example.myapplication.ViewModel.RequeteIAViewModel;
import com.example.myapplication.databinding.FragmentChatIABinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChatIAFragment extends Fragment {

    private FragmentChatIABinding binding;
    private RequeteIAViewModel requeteIAViewModel;
    private AdapterChatMessage chatMessageAdapter;
    private final List<RequeteIA> chatMessages = new ArrayList<>();
    private ReponseIARepository reponseIARepository;
    RecyclerView recyclerView;
    EditText inputchat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_chat_i_a, container, false);
        binding = FragmentChatIABinding.inflate(inflater, container, false);
        inputchat = binding.inputchat;
        requeteIAViewModel = new ViewModelProvider(requireActivity()).get(RequeteIAViewModel.class);

        //Initialisation de RecyclerView et du gestionnaire de disposition
        // Obtient la référence au RecyclerView à partir du layout lié
        recyclerView = binding.rvchatUser;
        //getActivity est l'équivalent de class.this
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        // Initialize the adapter
        chatMessageAdapter = new AdapterChatMessage(chatMessages);
        recyclerView.setAdapter(chatMessageAdapter);
        binding.sendmessage.setOnClickListener(this::sendMessage);

//        UtilsApiAI utilsApiAI = new UtilsApiAI(BuildConfig.OPENAI_API_KEY);
        return binding.getRoot();
    }
    //logique btn send msg
    public void sendMessage(View view) {
        String date = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        String message = Objects.requireNonNull(binding.inputchat.getText()).toString().trim();
        Boolean status = false;

        //solution temporaire à modifier avec l'id de user logged
        int idUtilisateur = getLoggedInUserId();

        if (message.length() >= 2 && !TextUtils.isEmpty(date)) {
            //faire appel methode asynchrone de l'api ici ?
            binding.inputchat.setText("");
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