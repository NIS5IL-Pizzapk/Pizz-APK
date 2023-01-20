package com.example.pizz_apk.models;

import com.google.gson.annotations.SerializedName;

public class SendMailResult {
    private String message;
    private String status;
    User user;


    public String getMail() {
        return mail;
    }
    @SerializedName("email")
    private String mail;
    private boolean success;

    public boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
