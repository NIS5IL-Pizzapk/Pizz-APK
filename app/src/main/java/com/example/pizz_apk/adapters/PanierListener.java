package com.example.pizz_apk.adapters;

import com.example.pizz_apk.models.PlatPropose;

public interface PanierListener {
    void onAddQuantity(PlatPropose plat);
    void onRemoveQuantity(PlatPropose plat);
    void onRemovePlat(PlatPropose plat);
}
