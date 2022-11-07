package com.example.pizz_apk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pizz_apk.adapters.RestaurantsAdapter;
import com.example.pizz_apk.databinding.ActivityRestaurantChoixBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RestaurantChoixActivity extends AppCompatActivity {

    private String[] listScrollingText;

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
        restaurantsList.add("Vauvert");
        RestaurantsAdapter restaurantsAdapter = new RestaurantsAdapter(restaurantsList,this);
        binding.rvHomepageRestaurants.setHasFixedSize(true);
        binding.rvHomepageRestaurants.setAdapter(restaurantsAdapter);
        binding.rvHomepageRestaurants.setLayoutManager(new LinearLayoutManager(this));


        TextView tv=(TextView)findViewById(R.id.scroll_text);
        tv.setSelected(true);

        //Récupération de la liste des textes à faire défiler
        listScrollingText = getResources().getStringArray(R.array.scrolling_text);

        int randomIndex = new Random().nextInt(listScrollingText.length);
        String randomName = listScrollingText[randomIndex];
        tv.setText(randomName);
        //OnClick du bouton temporaire permettant d'accéder à la page des pizzas
    }
}