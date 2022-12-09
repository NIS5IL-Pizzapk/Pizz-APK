package com.example.pizz_apk.models;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetroFitRequests<R> {
    @GET("/api/produit/getall")
    Call<RetroFitResponse<ArrayList<PlatPropose>>> getPlats();

    @POST("/api/user/signup")
     Call<Void> performRegistration();

    @GET("/api/user/login")
    Call<User> performLogin(@Query("email")String Email, @Query("password") String Password);

}
