package com.example.pizz_apk;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.pizz_apk.adapters.PlatUniqueListener;
import com.example.pizz_apk.databinding.FragmentPlatUniqueBinding;

import com.example.pizz_apk.models.PlatPropose;
import com.example.pizz_apk.viewmodels.PlatUniqueViewModel;

import java.util.List;


public class PlatUniqueFragment extends Fragment {

    FragmentPlatUniqueBinding binding;
    List<PlatPropose> platProposeList;
    PlatUniqueListener listener;

    Context context = getContext();

    public PlatUniqueFragment() {
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
        binding = FragmentPlatUniqueBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PlatUniqueViewModel platUniqueViewModel = new ViewModelProvider(requireActivity()).get(PlatUniqueViewModel.class);
        platUniqueViewModel.getSelectedPlat().observe(getViewLifecycleOwner(), plat -> {

            binding.tvPlatNom.setText(plat.getNom());
            binding.tvPlatPrix.setText(String.valueOf(plat.getPrix())+ " €");
            binding.tvPlatIngredients.setText(plat.getDescription());
            binding.imgPlat.setImageResource(plat.getImage());

            binding.btnPlatCommander.setOnClickListener(v -> {
                platUniqueViewModel.addPlatToPanier(plat);
                Navigation.findNavController(v).navigate(R.id.action_platUniqueFragment_to_nav_panier);
            });
        });
    }
}