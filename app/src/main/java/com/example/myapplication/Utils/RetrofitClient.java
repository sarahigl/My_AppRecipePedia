package com.example.myapplication.Utils;

import com.example.myapplication.Utils.API.ApiServiceIA;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static final String BASE_URL = "https://api.openai.com/";
    private static Retrofit retrofit;

    public static ApiServiceIA getApiServiceIA(){
        if (retrofit == null){
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            //instance retrofit
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        //creer une implementation de la classe ApiServiceIA
        return retrofit.create(ApiServiceIA.class);
    }

}
