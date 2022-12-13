package com.example.pizz_apk.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pizz_apk.models.PlatPropose;

import java.util.ArrayList;

public class ListeDessertsViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<PlatPropose>> listDessertsLiveData = new MutableLiveData<>(new ArrayList<>());


    public MutableLiveData<ArrayList<PlatPropose>> getListDessertsLiveData() {
        return listDessertsLiveData;
    }

    public void setListDessertsLiveData(ArrayList<PlatPropose> listDessertsLiveData) {
        this.listDessertsLiveData.setValue(listDessertsLiveData);
    }

}