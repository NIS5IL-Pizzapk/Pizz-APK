package com.example.pizz_apk.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;



public interface RetroFitRequests {
    @GET("/api/produit/get_all")
    Call<RetroFitResponse<ArrayList<PlatPropose>>> getAllProduits();

    @POST("/api/produit/plat/by_type_et_restaurant")
    //fais la requete pour avoir les plats par type et par restaurant en envoyant un objet JSON avec le type et l'id du restaurant
    Call<RetroFitResponse<ArrayList<PlatPropose>>> getPlatsByTypeEtRestaurant(@Body HashMap<String, Integer> map);
    
    @POST("/api/user/login")
    Call<LoginResult> executeLogin(@Body HashMap<String, String> map);
    
    @POST("/api/user/signup")
    Call<Void> executeSignup(@Body HashMap<String, String> map);
    
    @POST("/api/adresse/add_adresse")
    Call<Void> executeAddAdresse(@Body HashMap<String, String> map);

    @GET("/api/produit/supplement/get_all")
    Call<RetroFitResponse<ArrayList<PlatPropose>>> getAllSupplements();
}
