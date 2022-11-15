package com.example.pizz_apk.models;

public class CategorieAccueil {
    private String nom;
    private int image;
    private String tag;

    public CategorieAccueil(String nom, int image, String tag) {
        this.nom = nom;
        this.image = image;
        this.tag = tag;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}

