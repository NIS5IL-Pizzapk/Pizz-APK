package com.example.pizz_apk.models;

import com.google.gson.annotations.SerializedName;

public class LoginResult {
    @SerializedName("email")
    private String mail;
    @SerializedName("username")
    private String username;

    public String getMail() {
        return mail;
    }

    public String getUsername() {
        return username;
    }
}
