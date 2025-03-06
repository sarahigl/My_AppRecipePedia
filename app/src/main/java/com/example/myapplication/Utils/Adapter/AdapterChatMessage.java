package com.example.myapplication.Utils.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.IA.ChatMessage;
import com.example.myapplication.databinding.BotMsgItemRvBinding;
import com.example.myapplication.databinding.UserMsgItemRvBinding;

import java.util.List;

public class AdapterChatMessage extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final List<ChatMessage> chatMessages;

    public AdapterChatMessage(List<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }

    //tells the RecyclerView what type of view to use for a given item in the list
    @Override
    public int getItemViewType(int position) {
        return chatMessages.get(position).getMessageType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == ChatMessage.TYPE_USER){
            UserMsgItemRvBinding binding = UserMsgItemRvBinding.inflate(LayoutInflater.from(parent.getContext()));
            return new UserViewHolder(binding);
        }else{
            BotMsgItemRvBinding bindingBot = BotMsgItemRvBinding.inflate(LayoutInflater.from(parent.getContext()));
            return new BotViewHolder(bindingBot);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d("DEBUG", "Position : " + position);
        ChatMessage chatMessage = chatMessages.get(position);
        if (holder instanceof UserViewHolder) {
            ((UserViewHolder) holder).bind(chatMessage);
        } else if (holder instanceof BotViewHolder) {
            ((BotViewHolder) holder).bind(chatMessage);
        }
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }
    public void addMessage(ChatMessage chatMessage) {
        chatMessages.add(chatMessage);
        notifyItemInserted(chatMessages.size() - 1);
    }
}
class UserViewHolder extends RecyclerView.ViewHolder {
    UserMsgItemRvBinding binding;
    TextView tvUserMsg;

    public UserViewHolder(UserMsgItemRvBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
        tvUserMsg = binding.tvUserMsg;

    }

    public void bind(ChatMessage chatMessage) {
        tvUserMsg.setText(chatMessage.getMessage());
    }
}
class BotViewHolder extends RecyclerView.ViewHolder {
    BotMsgItemRvBinding binding;
    TextView tvBotMsg;

    public BotViewHolder(BotMsgItemRvBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
        tvBotMsg = binding.tvBotMsg;
    }

    public void bind(ChatMessage chatMessage) {
        tvBotMsg.setText(chatMessage.getMessage());
    }
}
