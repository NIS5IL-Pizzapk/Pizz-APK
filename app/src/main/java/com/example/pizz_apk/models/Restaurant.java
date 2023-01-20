package com.example.pizz_apk.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Restaurant implements Serializable {
    @SerializedName("ville")
    private String ville;
    @SerializedName("id")
    private int id;

    public Restaurant(String ville, int id) {
        this.ville = ville;
        this.id = id;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
