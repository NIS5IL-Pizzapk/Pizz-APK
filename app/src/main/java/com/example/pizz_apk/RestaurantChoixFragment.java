package com.example.pizz_apk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pizz_apk.adapters.RestaurantsAdapter;
import com.example.pizz_apk.databinding.FragmentRestaurantChoixBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RestaurantChoixFragment extends Fragment {

    private String[] listScrollingText;

    List<String> restaurantsList = new ArrayList<>();

    FragmentRestaurantChoixBinding binding;

    Context context = getContext();

    TextView tv;

    public RestaurantChoixFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentRestaurantChoixBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        restaurantsList = new ArrayList<>();
        restaurantsList.add("Clarensac");
        restaurantsList.add("Vauvert");
        RestaurantsAdapter restaurantsAdapter = new RestaurantsAdapter(restaurantsList,context);
        binding.rvHomepageRestaurants.setHasFixedSize(true);
        binding.rvHomepageRestaurants.setAdapter(restaurantsAdapter);
        binding.rvHomepageRestaurants.setLayoutManager(new LinearLayoutManager(context));


        tv = (TextView) view.findViewById(R.id.scroll_text);
        tv.setSelected(true);

        //Récupération de la liste des textes à faire défiler
        listScrollingText = getResources().getStringArray(R.array.scrolling_text);

        int randomIndex = new Random().nextInt(listScrollingText.length);
        String randomName = listScrollingText[randomIndex];
        tv.setText(randomName);
        //OnClick du bouton temporaire permettant d'accéder à la page des pizzas


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRestaurantChoixBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}