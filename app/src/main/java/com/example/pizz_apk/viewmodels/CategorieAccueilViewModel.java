package com.example.pizz_apk.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pizz_apk.models.CategorieAccueil;


import java.util.ArrayList;
import java.util.List;

public class CategorieAccueilViewModel extends ViewModel {
    List<CategorieAccueil> categoriesList;
    MutableLiveData<CategorieAccueil> selectedCategorie = new MutableLiveData<>();

    public List<CategorieAccueil> getCategoriesList() {
        if (categoriesList == null) {
            categoriesList = new ArrayList<>();
        }
        return categoriesList;
    }

    public void setCategoriesList(List<CategorieAccueil> categoriesList) {
        this.categoriesList = categoriesList;
    }

    public LiveData<CategorieAccueil> getSelectedCategorie() {
        return selectedCategorie;
    }

    public void setSelectedCategorie(CategorieAccueil categorie) {
        selectedCategorie.postValue(categorie);
    }
}
