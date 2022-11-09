package com.example.pizz_apk.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pizz_apk.models.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsViewModel extends ViewModel {

    List<Restaurant> restaurantsList;
    MutableLiveData<Restaurant> selectedRestaurant = new MutableLiveData<>();

    public List<Restaurant> getRestaurantsList() {
        if (restaurantsList == null) {
            restaurantsList = new ArrayList<>();
        }
        return restaurantsList;
    }

    public void setRestaurantsList(List<Restaurant> restaurantsList) {
        this.restaurantsList = restaurantsList;
    }

    public LiveData<Restaurant> getSelectedRestaurant() {
        return selectedRestaurant;
    }

    public void setSelectedRestaurant(Restaurant restaurant) {
        selectedRestaurant.postValue(restaurant);
    }


}
