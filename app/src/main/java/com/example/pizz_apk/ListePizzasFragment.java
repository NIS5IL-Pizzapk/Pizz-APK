package com.example.pizz_apk;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pizz_apk.adapters.ListePizzasAdapter;
import com.example.pizz_apk.adapters.PlatUniqueListener;
import com.example.pizz_apk.databinding.FragmentListePizzasBinding;
import com.example.pizz_apk.databinding.FragmentPlatUniqueBinding;
import com.example.pizz_apk.viewmodels.CategorieAccueilViewModel;
import com.example.pizz_apk.viewmodels.ListePizzasViewModel;
import com.example.pizz_apk.viewmodels.PlatUniqueViewModel;


public class ListePizzasFragment extends Fragment {

    FragmentListePizzasBinding binding;
    ListePizzasViewModel pizzaListViewModel;
    PlatUniqueViewModel platUniqueViewModel;
    Context context = getContext();
    PlatUniqueListener listener;

    public ListePizzasFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentListePizzasBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pizzaListViewModel = new ViewModelProvider(this).get(ListePizzasViewModel.class);
        platUniqueViewModel = new ViewModelProvider(requireActivity()).get(PlatUniqueViewModel.class);
        ListePizzasAdapter listePizzasAdapteradapter = new ListePizzasAdapter(pizzaListViewModel.getListPizzaLiveData().getValue(),context, platPropose -> {
            platUniqueViewModel.setSelectedPlat(platPropose);
            Navigation.findNavController(view).navigate(R.id.action_listePizzasFragment_to_platUniqueFragment);
        });
        binding.rvPizzasListe.setHasFixedSize(true);
        binding.rvPizzasListe.setAdapter(listePizzasAdapteradapter);
        binding.rvPizzasListe.setLayoutManager(new LinearLayoutManager(context));

        //NE RIEN TOUCHER A PARTIR D'ICI
        pizzaListViewModel.getListPizzaLiveData().observe(getViewLifecycleOwner(), pizzas -> {
            listePizzasAdapteradapter.setPizzasList(pizzaListViewModel.getOldSize(),pizzas);
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
}