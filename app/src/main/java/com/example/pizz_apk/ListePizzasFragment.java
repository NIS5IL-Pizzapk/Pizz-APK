package com.example.pizz_apk;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pizz_apk.adapters.ListePizzasAdapter;
import com.example.pizz_apk.databinding.FragmentListePizzasBinding;
import com.example.pizz_apk.viewmodels.ListePizzasViewModel;


public class ListePizzasFragment extends Fragment {

    FragmentListePizzasBinding binding;
    ListePizzasViewModel pizzaListViewModel;
    Context context = getContext();

    public ListePizzasFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentListePizzasBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        pizzaListViewModel = new ViewModelProvider(this).get(ListePizzasViewModel.class);
        ListePizzasAdapter adapter = new ListePizzasAdapter(pizzaListViewModel.getListPizzaLiveData().getValue(),context);
        binding.rvPizzasListe.setHasFixedSize(true);
        binding.rvPizzasListe.setAdapter(adapter);
        binding.rvPizzasListe.setLayoutManager(new LinearLayoutManager(context));

        //NE RIEN TOUCHER A PARTIR D'ICI
        pizzaListViewModel.getListPizzaLiveData().observe(this, pizzas -> {
            adapter.setPizzasList(pizzaListViewModel.getOldSize(),pizzas);
        });
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_liste_pizzas, container, false);
    }
}