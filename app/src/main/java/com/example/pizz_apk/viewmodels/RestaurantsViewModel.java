package com.example.pizz_apk.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pizz_apk.models.PlatPropose;
import com.example.pizz_apk.models.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsViewModel extends ViewModel {

    MutableLiveData<Restaurant> selectedRestaurant = new MutableLiveData<>();
    private final ArrayList<Restaurant> listRestaurantLiveData = new ArrayList<>();


    public ArrayList<Restaurant> getListRestaurantLiveData() {
        return listRestaurantLiveData;
    }

    public void setListRestaurantLiveData(ArrayList<Restaurant> listRestaurantLiveData) {
        //supprimer les anciens restaurants
        this.listRestaurantLiveData.clear();
        this.listRestaurantLiveData.addAll(listRestaurantLiveData);
    }

    public LiveData<Restaurant> getSelectedRestaurant() {
        return selectedRestaurant;
    }

    public void setSelectedRestaurant(Restaurant restaurant) {
        selectedRestaurant.postValue(restaurant);
    }


}
