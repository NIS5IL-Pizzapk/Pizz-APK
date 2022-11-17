package com.example.pizz_apk.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pizz_apk.models.PlatPropose;

import java.util.ArrayList;
import java.util.List;

public class PlatUniqueViewModel extends ViewModel {
    List<PlatPropose> platsList;
    MutableLiveData<PlatPropose> selectedPlat = new MutableLiveData<>();

    public List<PlatPropose> getPlatsList() {
        if (platsList == null) {
            platsList = new ArrayList<>();
        }
        return platsList;
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
}
