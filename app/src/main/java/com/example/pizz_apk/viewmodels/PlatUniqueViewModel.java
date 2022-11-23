package com.example.pizz_apk.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pizz_apk.models.PlatPropose;

import java.util.ArrayList;
import java.util.List;

public class PlatUniqueViewModel extends ViewModel {
    List<PlatPropose> platsList;
    MutableLiveData<List<PlatPropose>> panier = new MutableLiveData<>();
    MutableLiveData<PlatPropose> selectedPlat = new MutableLiveData<>();

    public List<PlatPropose> getPlatsList() {
        if (platsList == null) {
            platsList = new ArrayList<>();
        }
        return platsList;
    }

    // récupération du panier
    public LiveData<List<PlatPropose>> getPanier() {
        if (panier.getValue() == null) {
            panier.setValue(new ArrayList<>());
        }
        return panier;
    }

    // ajout d'un plat au panier
    public void addPlatToPanier(PlatPropose plat) {
        if (panier.getValue() == null) {
            panier.setValue(new ArrayList<PlatPropose>());
        }
        panier.getValue().add(plat);
        panier.postValue(panier.getValue());
    }

    // suppression d'un plat du panier
    public void removePlatFromPanier(PlatPropose plat) {
        if (panier.getValue() == null) {
            panier.setValue(new ArrayList<PlatPropose>());
        }
        panier.getValue().remove(plat);
        panier.postValue(panier.getValue());
    }

    public void addQuantityForSelectedPlat() {
        if (selectedPlat.getValue() != null) {
            selectedPlat.getValue().setQuantite(selectedPlat.getValue().getQuantite() + 1);
            selectedPlat.postValue(selectedPlat.getValue());
        }
    }


    public void setPlatsList(List<PlatPropose> platsList) {
        this.platsList = platsList;
    }

    public LiveData<PlatPropose> getSelectedPlat() {
        return selectedPlat;
    }

    public void setSelectedPlat(PlatPropose plat) {
        selectedPlat.postValue(plat);
    }



    // pour tous les plats de la liste, on calcule le prix total du panier en fonction de la quantité
    public float getTotalPrice() {
        float total = 0;
        if (panier.getValue() != null) {
            for (PlatPropose plat : panier.getValue()) {
                total += plat.getPrix() * plat.getQuantite();
            }
        }
        return total;
    }
}
