package com.example.myapplication.Utils.API;

import com.example.myapplication.BuildConfig;
import com.example.myapplication.Model.JSONParsing.OpenAiReponse;
import com.example.myapplication.Model.JSONParsing.OpenAiRequete;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiServiceIA {

    @Headers({

            "Content-Type: application/json",
            "Authorization: Bearer "+ BuildConfig.OPENAI_API_KEY
    })
    @POST("v1/chat/completions")
    Call<OpenAiReponse> createRequest(@Body OpenAiRequete openAiRequete);
}
