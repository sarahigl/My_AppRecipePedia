package com.example.myapplication.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ChatIAViewModel extends ViewModel {

    private final MutableLiveData<String> message = new MutableLiveData<>();
    private final MutableLiveData<String> date = new MutableLiveData<>();

    public void setMessage(String corp) {
        message.setValue(corp);
    }

    public MutableLiveData<String> getMessage() {
        return message;
    }

    public MutableLiveData<String> getDate() {
        return date;
    }


}
