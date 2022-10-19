package com.example.pizz_apk.viewmodels;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizz_apk.models.PlatPropose;
import com.example.pizz_apk.models.TestData;

import java.util.ArrayList;
import java.util.List;

public class ListePizzasViewModel extends ViewModel {

    private List<PlatPropose> listPizza = new ArrayList<>();
    private boolean baseCreme = false;

    public void setBase(String base, RecyclerView.Adapter adapter) {
        int i = listPizza.size();
        this.listPizza.clear();
        adapter.notifyItemRangeRemoved(0, i);
        this.listPizza.addAll(PlatPropose.getPlatsByTagNameFromList(TestData.listePizzas, base));
        //this.listPizza.add(new PlatPropose("Pizza 1", "Pizza 1", "Pizza", 0, null, null));
        adapter.notifyItemRangeInserted(0, listPizza.size());

    }

    public List<PlatPropose> getListPizza() {
        return listPizza;
    }

    public boolean isBaseCreme() {
        return baseCreme;
    }

    public void setBaseCreme(boolean baseCreme) {
        this.baseCreme = baseCreme;
    }
}
