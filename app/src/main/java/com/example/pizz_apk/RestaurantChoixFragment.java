package com.example.pizz_apk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pizz_apk.adapters.RestaurantsAdapter;
import com.example.pizz_apk.adapters.RestaurantsListener;
import com.example.pizz_apk.databinding.FragmentRestaurantChoixBinding;
import com.example.pizz_apk.models.Restaurant;
import com.example.pizz_apk.models.TestData;
import com.example.pizz_apk.viewmodels.RestaurantsViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RestaurantChoixFragment extends Fragment {

    private String[] listScrollingText;

    List<Restaurant> restaurantsList = new ArrayList<>();

    FragmentRestaurantChoixBinding binding;

    RestaurantsViewModel restaurantsViewModel;

    SharedPrefManager sharedPrefManager;


    TextView tv;

    public RestaurantChoixFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.restaurantsList = TestData.listeRestaurant;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRestaurantChoixBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }
    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        restaurantsViewModel = new ViewModelProvider(requireActivity()).get(RestaurantsViewModel.class);
        restaurantsViewModel.setRestaurantsList(restaurantsList);

        RestaurantsAdapter restaurantsAdapter = new RestaurantsAdapter(restaurantsViewModel.getRestaurantsList(),getContext(), restaurant -> {
            restaurantsViewModel.setSelectedRestaurant(restaurant);
            Navigation.findNavController(view).navigate(R.id.action_restaurantChoixFragment_to_accueilFragment);

        });
        binding.rvHomepageRestaurants.setHasFixedSize(true);
        binding.rvHomepageRestaurants.setAdapter(restaurantsAdapter);
        binding.rvHomepageRestaurants.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.scrollText.setSelected(true);
        //Récupération de la liste des textes à faire défiler
        listScrollingText = getResources().getStringArray(R.array.scrolling_text);

        int randomIndex = new Random().nextInt(listScrollingText.length);
        String randomName = listScrollingText[randomIndex];
        binding.scrollText.setText(randomName);
    }
}