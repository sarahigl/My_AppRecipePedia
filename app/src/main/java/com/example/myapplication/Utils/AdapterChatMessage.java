package com.example.myapplication.Utils;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.RequeteIA;
import com.example.myapplication.databinding.UserMsgItemRvBinding;

import java.util.List;

public class AdapterChatMessage extends RecyclerView.Adapter<ViewHolder>{

    private List<RequeteIA> chatMessages;

    public AdapterChatMessage(List<RequeteIA> chatMessages) {
        this.chatMessages = chatMessages;
    }

    public AdapterChatMessage() {
    }

    public List<RequeteIA> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(List<RequeteIA> chatMessages) {
        this.chatMessages = chatMessages;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_msg_item_rv, parent, false);
        UserMsgItemRvBinding binding = UserMsgItemRvBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("DEBUG", "Position : " + position);
        RequeteIA chatMessage = chatMessages.get(position);
        holder.tvUserMsg.setText(chatMessage.getCorpRequete());
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }
    public void addMessage(RequeteIA message) {
        chatMessages.add(message);
        notifyItemInserted(chatMessages.size() - 1);
    }
}
class ViewHolder extends RecyclerView.ViewHolder {
    UserMsgItemRvBinding binding;
    TextView tvUserMsg;

    public ViewHolder(UserMsgItemRvBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
        tvUserMsg = binding.tvUserMsg;

    }

}