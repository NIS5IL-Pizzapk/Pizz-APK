package com.example.pizz_apk.models;

public class Restaurant {
    private String nom;

    public Restaurant(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "nom='" + nom + '\'' +
                '}';
    }
}
