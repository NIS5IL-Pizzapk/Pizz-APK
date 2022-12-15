package com.example.pizz_apk.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlatPropose implements Serializable {
    @SerializedName("id")
    private int id;
    private String type; // à noter que le type doit obligatoirement être "pizza_rouge", "pizza_blanche", "burger", "dessert" ou "boisson"
    private String nom;
    private String description;
    private float prix;
    private List<Allergene> listeAllergenes;
    private String image;
    private List<Tag> tags;
    private int quantite;

    public PlatPropose(String type, String nom, String description, float prix, List<Allergene> listeAllergenes,List<Tag> tags,String image,int quantite) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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




    public static ArrayList<PlatPropose> getPlatsByTagNameFromList(ArrayList<PlatPropose> list, String tag) {
        ArrayList<PlatPropose> newList = new ArrayList<>();
        for(PlatPropose plat : list) {
            for(Tag t : plat.getTags()) {
                if(t.getNom().equals(tag)) {newList.add(plat);}
            }
        }
        return newList;
    }
}
