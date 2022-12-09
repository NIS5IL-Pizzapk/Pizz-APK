package com.example.pizz_apk.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pizz_apk.models.PlatPropose;

import java.util.ArrayList;

public class ListeBoissonsViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<PlatPropose>> listBoissonsLiveData = new MutableLiveData<>(new ArrayList<>());


    public MutableLiveData<ArrayList<PlatPropose>> getListBoissonsLiveData() {
        return listBoissonsLiveData;
    }

    public void setListBoissonsLiveData(ArrayList<PlatPropose> listBoissonsLiveData) {
        this.listBoissonsLiveData.setValue(listBoissonsLiveData);
    }

}
