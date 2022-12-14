package com.example.pizz_apk.models;

import com.google.gson.annotations.SerializedName;

public class LoginResult {
    @SerializedName("email")
    private String mail;
    @SerializedName("password")
    private String password;

    private String token;

    public String getToken() {
        return token;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }


}
