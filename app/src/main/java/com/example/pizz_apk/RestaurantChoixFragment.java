package com.example.pizz_apk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pizz_apk.adapters.RestaurantsAdapter;
import com.example.pizz_apk.databinding.FragmentRestaurantChoixBinding;

import java.util.ArrayList;
import java.util.List;


public class RestaurantChoixFragment extends Fragment {

    FragmentRestaurantChoixBinding binding;
    List<String> restaurantsList = new ArrayList<>();
    Context context = getContext();


    public RestaurantChoixFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = binding.getRoot();
        restaurantsList = new ArrayList<>();
        restaurantsList.add("Clarensac");
        restaurantsList.add("Nîmes");
        RestaurantsAdapter restaurantsAdapter = new RestaurantsAdapter(restaurantsList,context);
        binding.rvHomepageRestaurants.setHasFixedSize(true);
        binding.rvHomepageRestaurants.setAdapter(restaurantsAdapter);
            binding.rvHomepageRestaurants.setLayoutManager(new LinearLayoutManager(context));
        //OnClick du bouton temporaire permettant d'accéder à la page des pizzas
        binding.btnTemp.setOnClickListener(view1 -> {
            Navigation.findNavController(view1).navigate(R.id.action_restaurantChoixFragment_to_accueilFragment);
        });

        binding.btnTemp2.setOnClickListener(view1 -> {
            Intent intent = new Intent().setClass(context, AccueilFragment.class);
            startActivity(intent);
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRestaurantChoixBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}