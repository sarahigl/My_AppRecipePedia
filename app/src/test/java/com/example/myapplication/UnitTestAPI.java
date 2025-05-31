package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.example.myapplication.Utils.API.ApiServiceIA;
import com.example.myapplication.Utils.RetrofitClient;

import org.junit.Test;

public class UnitTestAPI {

    @Test
    public void testApiServiceIA_NotNull() {
        // TODO : instanciation de RetrofitClient (classe de Retrofit qui fait appel à l'API)
        ApiServiceIA apiService = RetrofitClient.getApiServiceIA();
        // TODO : assertion qui vérifie que l'objet retourné n'est pas null
        assertNotNull("L'instance de ApiServiceIA ne doit pas être null", apiService);
    }
    @Test
    public void setApiServiceIA_TypeInstance(){
        ApiServiceIA apiService = RetrofitClient.getApiServiceIA();
        /*TODO: Assertion qui vérifie que l'objet retourné est bien une instance de
        TODO: ApiServiceIA (contient les endpoints de l'API) */
        assertTrue("L'objet retourné doit être une instance de ApiServiceIA", apiService instanceof ApiServiceIA);
    }

}
