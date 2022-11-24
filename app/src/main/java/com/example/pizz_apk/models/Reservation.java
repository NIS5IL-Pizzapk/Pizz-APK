package com.example.pizz_apk.models;

import java.sql.Time;
import java.util.Date;

public class Reservation {
    private String nom;
    private String prenom;
    private String telephone;
    private int nbPersonnes;
    private Date date;
    private Time heure;

    public Reservation(String nom, String prenom, String telephone, int nbPersonnes, Date date, Time heure) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.nbPersonnes = nbPersonnes;
        this.date = date;
        this.heure = heure;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }

    public void setNbPersonnes(int nbPersonnes) {
        this.nbPersonnes = nbPersonnes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getHeure() {
        return heure;
    }

    public void setHeure(Time heure) {
        this.heure = heure;
    }


}
