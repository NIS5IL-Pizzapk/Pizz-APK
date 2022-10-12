package com.example.pizz_apk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pizz_apk.adapters.RestaurantsAdapter;
import com.example.pizz_apk.databinding.ActivityRestaurantChoixBinding;

import java.util.ArrayList;
import java.util.List;

public class RestaurantChoixActivity extends AppCompatActivity {

    List<String> restaurantsList = new ArrayList<>();
    ActivityRestaurantChoixBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_choix);
        binding = ActivityRestaurantChoixBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        restaurantsList = new ArrayList<>();
        restaurantsList.add("Clarensac");
        restaurantsList.add("Nîmes");
        RestaurantsAdapter restaurantsAdapter = new RestaurantsAdapter(restaurantsList,this);
        binding.rvHomepageRestaurants.setHasFixedSize(true);
        binding.rvHomepageRestaurants.setAdapter(restaurantsAdapter);
        binding.rvHomepageRestaurants.setLayoutManager(new LinearLayoutManager(this));
        //OnClick du bouton temporaire permettant d'accéder à la page des pizzas
        binding.btnTemp.setOnClickListener(view1 -> {
            Intent intent = new Intent().setClass(this, ListePizzasActivity.class);
            startActivity(intent);
        });
    }
}