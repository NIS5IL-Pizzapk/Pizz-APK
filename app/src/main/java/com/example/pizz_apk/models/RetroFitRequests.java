package com.example.pizz_apk.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface RetroFitRequests {
    //get current user


    @GET("/api/produit/get_all")
    Call<RetroFitResponse<ArrayList<PlatPropose>>> getAllProduits();

    @POST("/api/produit/plat/by_type_et_restaurant")
    //fais la requete pour avoir les plats par type et par restaurant en envoyant un objet JSON avec le type et l'id du restaurant
    Call<RetroFitResponse<ArrayList<PlatPropose>>> getPlatsByTypeEtRestaurant(@Body HashMap<String, Integer> map);

    @FormUrlEncoded
    @POST("/api/user/login")
    Call<LoginResult> Login(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("/api/user/send_mail")
    Call<SendMailResult> SendEmail(
            @Field("email") String email
    );


    @POST("/api/user/signup")
    Call<Void> executeSignup(@Body HashMap<String, String> map);

    @POST("/api/user/signup")
    Call<Void> executeGuest(@Body HashMap<String, String> map);

    @HTTP(method = "GET", path = "/api/user/by_id/{id}")
    Call<LoginResult> getUserByIdLogin(@Path("id") int id, @Header("Authorization") String token);

    @HTTP(method = "GET", path = "/api/user/by_id/{id}")
    Call<RetroFitResponse<User>> getUserById(@Path("id") int id);

    @POST("/api/adresse/add_adresse")
    Call<Void> executeAddAdresse(@Body HashMap<String, String> map);

//    @POST("/api/user/send_mail")
//    Call<Void> executeSendMail(@Body HashMap<String, String> map);

    @POST("/api/user/update/")
    Call<RetroFitResponse<User>> executeUpdate(int userID, String valueUsername, String valuePassword, String valueEmail, String valueAdresse, String valueTelephone);
    @GET("/api/produit/supplement/get_all")
    Call<RetroFitResponse<ArrayList<PlatPropose>>> getAllSupplements();

    @POST("/api/produit/supplement/by_type_et_restaurant")
        //fais la requete pour avoir les supplements par type et par restaurant en envoyant un objet JSON avec le type et l'id du restaurant
    Call<RetroFitResponse<ArrayList<PlatPropose>>> getSupplementsByTypeEtRestaurant(@Body HashMap<String, Integer> map);
}
