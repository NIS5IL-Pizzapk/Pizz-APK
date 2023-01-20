package com.example.pizz_apk.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;

public class User implements Serializable {
    @SerializedName("id")
    private int id;
    private String username;
    private String password;
    private String email;
    private String adresseLivraison;
    private String token;
    private String telephone;

    public User(int id,String username, String password, String email, String adresseLivraison, String telephone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.adresseLivraison = adresseLivraison;
        this.telephone = telephone;
    }

    public int getId() {
        return id;
    }

    public void setUser(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresseLivraison() {
        return adresseLivraison;
    }

    public void setAdresseLivraison(String adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

}
