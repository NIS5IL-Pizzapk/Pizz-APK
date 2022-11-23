package com.example.pizz_apk.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlatPropose implements Serializable {
    private int id;
    private String type;
    private String nom;
    private String description;
    private float prix;
    private List<Allergene> listeAllergenes;
    private int image;
    private List<Tag> tags;
    private int quantite;

    public PlatPropose(String type, String nom, String description, float prix, List<Allergene> listeAllergenes,List<Tag> tags,int image,int quantite) {
        this.type = type;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.listeAllergenes = listeAllergenes;
        this.tags = tags;
        this.image = image;
        this.quantite = quantite;
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

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void addQuantite(int quantite) {
        this.quantite += quantite;
    }

    public void removeQuantite(int quantite) {
        this.quantite -= quantite;
    }

    public void PricePerQuantity() {
        this.prix = this.prix * this.quantite;
    }




    public static List<PlatPropose> getPlatsByTagNameFromList(List<PlatPropose> list, String tag) {
        List<PlatPropose> newList = new ArrayList<>();
        for(PlatPropose plat : list) {
            for(Tag t : plat.getTags()) {
                if(t.getNom().equals(tag)) {newList.add(plat);}
            }
        }
        return newList;
    }
}
