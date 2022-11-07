package com.example.pizz_apk.models;

import java.io.Serializable;

public class Allergene implements Serializable {
    private int id;
    private String nom;

    public Allergene(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
