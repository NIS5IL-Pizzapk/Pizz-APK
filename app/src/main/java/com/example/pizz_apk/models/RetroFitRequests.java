package com.example.pizz_apk.models;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetroFitRequests {
    @GET("/api/produit/getall")
    Call<RetroFitResponse<ArrayList<PlatPropose>>> getPlats();

    @GET("/api/user/signup")
        //Call<User> performRegistration(@Query("username") String Username, @Query("adresse") String Adresse, String Email, String Password);
    Call<User> performRegistration(@Query("username") String Username, @Query("email") String Email, @Query("password") String Password);

    @GET("/api/user/login")
    Call<User> performLogin(@Query("email")String Email, @Query("password") String Password);
}
