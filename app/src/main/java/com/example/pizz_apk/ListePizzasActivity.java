package com.example.pizz_apk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.pizz_apk.adapters.ListePizzasAdapter;
import com.example.pizz_apk.databinding.ActivityListePizzasBinding;
import com.example.pizz_apk.models.PlatPropose;
import com.example.pizz_apk.models.TestData;
import com.example.pizz_apk.viewmodels.ListePizzasViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListePizzasActivity extends AppCompatActivity {

    ActivityListePizzasBinding binding;
    ListePizzasViewModel pizzaListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_pizzas);
        binding = ActivityListePizzasBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        pizzaListViewModel = new ViewModelProvider(this).get(ListePizzasViewModel.class);
        ListePizzasAdapter adapter = new ListePizzasAdapter(pizzaListViewModel.getListPizzaLiveData().getValue(),this);
        binding.rvPizzasListe.setHasFixedSize(true);
        binding.rvPizzasListe.setAdapter(adapter);
        binding.rvPizzasListe.setLayoutManager(new LinearLayoutManager(this));
        pizzaListViewModel.getListPizzaLiveData().observe(this, pizzas -> {
            adapter.setPizzasList(pizzaListViewModel.getOldSize(),pizzas);
        });


        //NE RIEN TOUCHER A PARTIR D'ICI !
        binding.btnBaseTomate.setOnClickListener(view1 -> {
            pizzaListViewModel.setBaseLD("Rouge");
            binding.btnBaseTomate.setEnabled(false);
            binding.btnBaseCreme.setEnabled(true);
            pizzaListViewModel.setBaseCreme(false);
        });
        binding.btnBaseCreme.setOnClickListener(view1 -> {
            pizzaListViewModel.setBaseLD("Blanche");
            binding.btnBaseTomate.setEnabled(true);
            binding.btnBaseCreme.setEnabled(false);
            pizzaListViewModel.setBaseCreme(true);
        });
        if(!pizzaListViewModel.isBaseCreme()){
            binding.btnBaseTomate.callOnClick();
        } else {
            binding.btnBaseCreme.callOnClick();
        }

    }
}