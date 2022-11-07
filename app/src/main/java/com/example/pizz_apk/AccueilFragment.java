package com.example.pizz_apk;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pizz_apk.adapters.CategorieAccueilAdapter;
import com.example.pizz_apk.databinding.FragmentAccueilBinding;
import com.example.pizz_apk.databinding.FragmentRestaurantChoixBinding;
import com.example.pizz_apk.models.CategorieAccueil;
import com.example.pizz_apk.models.TestData;

import java.util.ArrayList;
import java.util.List;


public class AccueilFragment extends Fragment {

    FragmentAccueilBinding binding;
    List<CategorieAccueil> categorieAccueilList = new ArrayList<>();
    Context context = getContext();

    public AccueilFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentAccueilBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        categorieAccueilList = TestData.ListeCatégorie;
        CategorieAccueilAdapter adapter = new CategorieAccueilAdapter(categorieAccueilList,context);
        binding.rvButtonsAccueil.setHasFixedSize(true);
        binding.rvButtonsAccueil.setAdapter(adapter);
        binding.rvButtonsAccueil.setLayoutManager(new LinearLayoutManager(context));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAccueilBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}