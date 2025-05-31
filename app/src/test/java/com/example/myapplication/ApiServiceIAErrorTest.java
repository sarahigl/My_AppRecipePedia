package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import com.example.myapplication.Model.IA.DTO.MessageDTO;
import com.example.myapplication.Model.IA.JSONParsing.OpenAiReponse;
import com.example.myapplication.Model.IA.JSONParsing.OpenAiRequete;
import com.example.myapplication.Utils.API.ApiServiceIA;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceIAErrorTest {
    private MockWebServer mockWebServer;
    private ApiServiceIA apiService;

    @Before
    public void setUp() throws Exception {
        //TODO: On initialise le MockWebServer une fois
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        //TODO: On pointe Retrofit vers MockWebServer
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //TODO: On initialise l'API service
        apiService = retrofit.create(ApiServiceIA.class);
    }

    @After
    public void tearDown() throws Exception {
        //TODO: On arrête le MockWebServer une fois terminé
        mockWebServer.shutdown();
    }

    @Test
    public void createRequest_handles401Error() throws Exception {
        //TODO: 1) Simule un 401 Unauthorized avec un message d'erreur
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(401)
                .setBody("{\"error\":\"Unauthorized\"}"));

        //TODO: 2) Prépare la requête minimale (model + messages)
        OpenAiRequete req = new OpenAiRequete(
                "gpt-4o-mini",
                Collections.singletonList(new MessageDTO("user", "test"))
        );

        //TODO: 3) Exécute synchroniquement l'appel
        Call<OpenAiReponse> call = apiService.createRequest(req);
        Response<OpenAiReponse> response = call.execute();

        //TODO: 4) Assertions de la réponse (401 Unauthorized)
        assertFalse("La réponse ne doit pas être successful", ((Response<?>) response).isSuccessful());
        assertEquals("Le code d'erreur doit être 401", 401, response.code());
    }
}
