package com.example.myapplication;

import static org.junit.Assert.*;

import com.example.myapplication.Model.IA.DTO.MessageDTO;
import com.example.myapplication.Model.IA.JSONParsing.OpenAiReponse;
import com.example.myapplication.Model.IA.JSONParsing.OpenAiRequete;
import com.example.myapplication.Utils.API.ApiServiceIA;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Collections;

public class OpenAIServiceTest {

    private MockWebServer mockWebServer;
    private ApiServiceIA apiService;

    @Before
    public void setUp() throws Exception {
        mockWebServer = new MockWebServer();
        //TODO: On lance le MockWebServer
        mockWebServer.start();

        //TODO: On pointe Retrofit vers MockWebServer
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiServiceIA.class);
    }

    @After
    public void tearDown() throws Exception {
        //TODO: On arrête le MockWebServer une fois terminé
        mockWebServer.shutdown();
    }

    @Test
    public void createRequest_parsingResponse_bodyNotNullAndMessageCorrect() throws Exception {
        //TODO: 1) On prépare un JSON factice identique à ce qu'OpenAI renverrait
        String fakeJson = "{\n" +
                "  \"choices\": [\n" +
                "    { \"message\": { \"content\": \"Recette test : lasagne végé\" } }\n" +
                "  ]\n" +
                "}";

        //TODO: 2) On queue la réponse HTTP 200 avec ce body
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(fakeJson));

        //TODO: 3) On construit une requête minimale (model + messages)
        OpenAiRequete req = new OpenAiRequete(
                "gpt-4o-mini",
                //singletonlist => retourne une liste avec un seul élément
                Collections.singletonList(
                        new MessageDTO("user", "test")
                )
        );

        //TODO: 4) On exécute synchroniquement l'appel à l'API
        Call<OpenAiReponse> call = apiService.createRequest(req);
        retrofit2.Response<OpenAiReponse> response = call.execute();

        //TODO: 5) Assertions de la réponse
        assertTrue("La réponse doit être successful", response.isSuccessful());
        assertNotNull("Le body ne doit pas être null", response.body());

        //TODO: 6) On vérifie que le message est correctement désérialisé et qu'il est correct
        OpenAiReponse body = response.body();
        assertEquals(
                "Le message doit être correctement désérialisé",
                "Recette test : lasagne végé",
                body.getMessage()
        );
    }
}
