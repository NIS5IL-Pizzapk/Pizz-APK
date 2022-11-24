package com.example.pizz_apk;

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
import com.example.pizz_apk.adapters.PanierAdapter;
import com.example.pizz_apk.adapters.PanierListener;
import com.example.pizz_apk.adapters.PlatUniqueListener;
import com.example.pizz_apk.databinding.FragmentPanierBinding;
import com.example.pizz_apk.databinding.FragmentPlatUniqueBinding;
import com.example.pizz_apk.models.PlatPropose;
import com.example.pizz_apk.viewmodels.PlatUniqueViewModel;

import java.util.List;
import java.util.Locale;

public class PanierFragment extends Fragment {

    FragmentPanierBinding binding;
    PlatUniqueListener listener;
    PlatUniqueViewModel platUniqueViewModel;
    ListePizzasAdapter adapter;


    public PanierFragment() {
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
        binding = FragmentPanierBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        platUniqueViewModel = new ViewModelProvider(requireActivity()).get(PlatUniqueViewModel.class);
        binding.tvPrixTotalPanier.setText("Prix total " + platUniqueViewModel.getTotalPrice() + " €");
        PanierAdapter panierAdapter = new PanierAdapter(platUniqueViewModel.getPanier(), getContext(), new PanierListener() {
            @Override
            public void onAddQuantity(PlatPropose plat) {
                binding.tvPrixTotalPanier.setText("Prix total " + platUniqueViewModel.getTotalPrice() + " €");

            }

            @Override
            public void onRemoveQuantity(PlatPropose plat) {
                binding.tvPrixTotalPanier.setText("Prix total " + platUniqueViewModel.getTotalPrice() + " €");
            }

            @Override
            public void onRemovePlat(PlatPropose plat) {
                platUniqueViewModel.removePlatFromPanier(plat);
                binding.tvPrixTotalPanier.setText("Prix total " + platUniqueViewModel.getTotalPrice() + " €");


            }
        });

        this.binding.rvItemPanier.setAdapter(panierAdapter);
        this.binding.rvItemPanier.setLayoutManager(new LinearLayoutManager(getContext()));
        this.binding.rvItemPanier.setHasFixedSize(true);
    }
}