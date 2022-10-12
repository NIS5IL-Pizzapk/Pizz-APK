package com.example.pizz_apk.models;

public class PlatPropose {
    private String type;
    private String nom;
    private String description;
    private float prix;
    private String listeAllergenes;

    public PlatPropose(String type, String nom, String description, float prix, String listeAllergenes) {
        this.type = type;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.listeAllergenes = listeAllergenes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getListeAllergenes() {
        return listeAllergenes;
    }

    public void setListeAllergenes(String listeAllergenes) {
        this.listeAllergenes = listeAllergenes;
    }
}
