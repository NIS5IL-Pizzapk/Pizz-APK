package com.example.pizz_apk.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pizz_apk.models.PlatPropose;

import java.util.ArrayList;
import java.util.Objects;

public class ListeBurgersViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<PlatPropose>> listBurgersLiveData = new MutableLiveData<>(new ArrayList<>());


    public MutableLiveData<ArrayList<PlatPropose>> getListBurgersLiveData() {
        return listBurgersLiveData;
    }

    public void setListBurgersLiveData(ArrayList<PlatPropose> listBurgersLiveData) {
        this.listBurgersLiveData.setValue(listBurgersLiveData);
    }

}
