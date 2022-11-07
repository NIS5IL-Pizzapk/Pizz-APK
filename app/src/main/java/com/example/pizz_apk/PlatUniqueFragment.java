package com.example.pizz_apk;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.pizz_apk.databinding.FragmentPlatUniqueBinding;

import com.example.pizz_apk.models.PlatPropose;


public class PlatUniqueFragment extends Fragment {
    FragmentPlatUniqueBinding binding;

    Context context = getContext();

    public PlatUniqueFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentPlatUniqueBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        //todo lignes à modifier
        //PlatPropose plat = (PlatPropose) getIntent().getSerializableExtra("plat");
        //binding.tvPlatUniqueNom.setText(plat.getNom());
        //binding.tvPlatUniquePrix.setText(String.valueOf(plat.getPrix()));
        //binding.tvPlatUniqueIngredients.setText(plat.getDescription());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPlatUniqueBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}