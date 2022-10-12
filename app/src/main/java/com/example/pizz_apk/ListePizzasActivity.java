package com.example.pizz_apk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.pizz_apk.adapters.ListePizzasAdapter;
import com.example.pizz_apk.databinding.ActivityListePizzasBinding;
import com.example.pizz_apk.models.PlatPropose;
import com.example.pizz_apk.models.TestData;

import java.util.ArrayList;
import java.util.List;

public class ListePizzasActivity extends AppCompatActivity {

    List<PlatPropose> listePizzas = new ArrayList<>();
    ActivityListePizzasBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_pizzas);
        binding = ActivityListePizzasBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        listePizzas = TestData.listePizzas;
        ListePizzasAdapter adapter = new ListePizzasAdapter(listePizzas,this);
        binding.rvPizzasListe.setHasFixedSize(true);
        binding.rvPizzasListe.setAdapter(adapter);
        binding.rvPizzasListe.setLayoutManager(new LinearLayoutManager(this));
    }
}