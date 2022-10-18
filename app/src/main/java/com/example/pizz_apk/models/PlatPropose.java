package com.example.pizz_apk.models;

import java.util.List;

public class PlatPropose {
    private int id;
    private String type;
    private String nom;
    private String description;
    private float prix;
    private List<Allergene> listeAllergenes;
    private List<Tag> tags;

    public PlatPropose(String type, String nom, String description, float prix, List<Allergene> listeAllergenes,List<Tag> tags) {
        this.type = type;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.listeAllergenes = listeAllergenes;
        this.tags = tags;
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

    public List<Allergene> getListeAllergenes() {
        return listeAllergenes;
    }

    public void setListeAllergenes(List<Allergene> listeAllergenes) {
        this.listeAllergenes = listeAllergenes;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public int getId() {
        return id;
    }
}
