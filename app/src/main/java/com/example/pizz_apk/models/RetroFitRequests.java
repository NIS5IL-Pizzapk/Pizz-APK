package com.example.pizz_apk.models;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetroFitRequests {
    @GET("/api/produit/getall")
    Call<RetroFitResponse<ArrayList<PlatPropose>>> getPlats();
}
