package com.example.pizz_apk.adapters;

import com.example.pizz_apk.models.PlatPropose;

public interface UpSellingListener {
    void onAddQuantity(PlatPropose platPropose);
    void onRemoveQuantity(PlatPropose platPropose);

}
