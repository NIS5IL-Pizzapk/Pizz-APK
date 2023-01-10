package com.example.pizz_apk.models;

import com.google.gson.annotations.SerializedName;

public class LoginResult {
    @SerializedName("email")
    private String mail;
    @SerializedName("password")
    private String password;
    @SerializedName("token")
    private String token;
    @SerializedName("id")
    private int id;
    @SerializedName("username")
    private String username;
    @SerializedName("adresse")
    private String adresse;
    @SerializedName("telephone")
    private String telephone;

    public String getToken() {
        return token;
    }


    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getAdresseLivraison() {
        return adresse;
    }

    public String getTelephone() {
        return telephone;
    }
}
