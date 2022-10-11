package com.example.pizz_apk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.pizz_apk.adapters.RestaurantsAdapter;
import com.example.pizz_apk.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class RestaurantChoixActivity extends AppCompatActivity {

    List<String> restaurantsList;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        restaurantsList = new ArrayList<>();
        restaurantsList.add("Clarensac");
        restaurantsList.add("Nîmes");
        RestaurantsAdapter restaurantsAdapter = new RestaurantsAdapter(restaurantsList,this);
        binding.rvHomepageRestaurants.setHasFixedSize(true);
        binding.rvHomepageRestaurants.setAdapter(restaurantsAdapter);
        binding.rvHomepageRestaurants.setLayoutManager(new LinearLayoutManager(this));
    }
}