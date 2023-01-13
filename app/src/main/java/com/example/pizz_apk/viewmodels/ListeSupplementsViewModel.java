package com.example.pizz_apk.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pizz_apk.models.PlatPropose;

import java.util.ArrayList;

public class ListeSupplementsViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<PlatPropose>> listSupplementsLiveData = new MutableLiveData<>(new ArrayList<>());


    public MutableLiveData<ArrayList<PlatPropose>> getListSupplementsLiveData() {
        return listSupplementsLiveData;
    }

    public void setListSupplementsLiveData(ArrayList<PlatPropose> listSupplementsLiveData) {
        this.listSupplementsLiveData.setValue(listSupplementsLiveData);
    }

}
