package com.example.pizz_apk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.pizz_apk.adapters.CategorieAccueilAdapter;
import com.example.pizz_apk.adapters.ListePizzasAdapter;
import com.example.pizz_apk.adapters.RestaurantsAdapter;
import com.example.pizz_apk.databinding.ActivityAccueilBinding;
import com.example.pizz_apk.databinding.ActivityListePizzasBinding;
import com.example.pizz_apk.databinding.ActivityRestaurantChoixBinding;
import com.example.pizz_apk.models.CategorieAccueil;
import com.example.pizz_apk.models.TestData;

import java.util.ArrayList;
import java.util.List;

public class AccueilActivity extends AppCompatActivity {
    ActivityAccueilBinding binding;
    List<CategorieAccueil> categorieAccueilList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        binding = ActivityAccueilBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        categorieAccueilList = TestData.ListeCatégorie;
        CategorieAccueilAdapter adapter = new CategorieAccueilAdapter(categorieAccueilList,this);
        binding.rvButtonsAccueil.setHasFixedSize(true);
        binding.rvButtonsAccueil.setAdapter(adapter);
        binding.rvButtonsAccueil.setLayoutManager(new LinearLayoutManager(this));
    }
}