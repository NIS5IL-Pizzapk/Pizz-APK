package com.example.pizz_apk.models;

import com.google.gson.annotations.SerializedName;

public class LoginResult {
    @SerializedName("email")
    private String mail;
    @SerializedName("password")
    private String password;

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }


}
