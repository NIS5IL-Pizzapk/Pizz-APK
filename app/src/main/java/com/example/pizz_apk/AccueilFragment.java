package com.example.pizz_apk;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pizz_apk.adapters.CategorieAccueilAdapter;
import com.example.pizz_apk.databinding.FragmentAccueilBinding;
import com.example.pizz_apk.databinding.FragmentPlatUniqueBinding;
import com.example.pizz_apk.models.CategorieAccueil;
import com.example.pizz_apk.models.TestData;

import java.util.ArrayList;
import java.util.List;


public class AccueilFragment extends Fragment {

    FragmentAccueilBinding binding;
    List<CategorieAccueil> categorieAccueilList = new ArrayList<>();
    public AccueilFragment() {
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
        binding = FragmentAccueilBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        categorieAccueilList = TestData.ListeCatégorie;
        CategorieAccueilAdapter adapter = new CategorieAccueilAdapter(categorieAccueilList,getContext());
        binding.rvButtonsAccueil.setHasFixedSize(true);
        binding.rvButtonsAccueil.setAdapter(adapter);
        binding.rvButtonsAccueil.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}