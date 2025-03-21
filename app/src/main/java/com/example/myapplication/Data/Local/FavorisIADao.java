package com.example.myapplication.Data.Local;

import com.example.myapplication.Model.IA.FavorisIA;

public interface FavorisIADao {
    long insertFavori(FavorisIA favorisIA);
    boolean isFavoriExistForUser(int userId);
    String addFavori(int userId) throws Exception;
}
