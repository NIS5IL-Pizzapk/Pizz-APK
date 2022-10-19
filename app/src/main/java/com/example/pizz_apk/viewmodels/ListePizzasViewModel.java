package com.example.pizz_apk.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizz_apk.models.PlatPropose;
import com.example.pizz_apk.models.TestData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListePizzasViewModel extends ViewModel {

    private int oldSize = 0;
    private final MutableLiveData<List<PlatPropose>> listPizzaLiveData = new MutableLiveData<>(new ArrayList<>());
    private boolean baseCreme = false;

    public void setBaseLD(String base) {
        this.oldSize = Objects.requireNonNull(listPizzaLiveData.getValue()).size();
        //this.listPizzaLiveData.getValue().clear();
        this.listPizzaLiveData.postValue(PlatPropose.getPlatsByTagNameFromList(TestData.listePizzas, base));
        //this.listPizzaLiveData.getValue().addAll(PlatPropose.getPlatsByTagNameFromList(TestData.listePizzas, base));
    }

    public boolean isBaseCreme() {
        return baseCreme;
    }

    public void setBaseCreme(boolean baseCreme) {
        this.baseCreme = baseCreme;
    }

    public MutableLiveData<List<PlatPropose>> getListPizzaLiveData() {
        return listPizzaLiveData;
    }

    public int getOldSize() {
        return oldSize;
    }
}
