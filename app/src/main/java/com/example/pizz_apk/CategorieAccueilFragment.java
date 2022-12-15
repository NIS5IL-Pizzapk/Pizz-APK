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

import com.example.pizz_apk.adapters.CategorieAccueilAdapter;
import com.example.pizz_apk.adapters.CategorieAccueilListener;
import com.example.pizz_apk.databinding.FragmentAccueilBinding;
import com.example.pizz_apk.models.CategorieAccueil;
import com.example.pizz_apk.models.TestData;
import com.example.pizz_apk.viewmodels.CategorieAccueilViewModel;

import java.util.ArrayList;
import java.util.List;


public class CategorieAccueilFragment extends Fragment {

    FragmentAccueilBinding binding;
    List<CategorieAccueil> categorieAccueilList = new ArrayList<>();
    CategorieAccueilViewModel categorieAccueilViewModel;
    public CategorieAccueilFragment() {
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
        categorieAccueilViewModel = new ViewModelProvider(requireActivity()).get(CategorieAccueilViewModel.class);
        categorieAccueilViewModel.setCategoriesList(categorieAccueilList);
        CategorieAccueilAdapter categorieAccueilAdapter = new CategorieAccueilAdapter(categorieAccueilViewModel.getCategoriesList(),getContext(), categorie -> {
            categorieAccueilViewModel.setSelectedCategorie(categorie);
            if (categorie.getTag() == "pizzas") {
                Navigation.findNavController(view).navigate(R.id.action_accueilFragment_to_listePizzasFragment);
            } else if (categorie.getTag() == "burgers") {
                Navigation.findNavController(view).navigate(R.id.action_accueilFragment_to_listeBurgersFragment);
            }else if (categorie.getTag() == "boissons") {
                Navigation.findNavController(view).navigate(R.id.action_accueilFragment_to_listeBoissonsFragment);
            }else if (categorie.getTag() == "desserts") {
                Navigation.findNavController(view).navigate(R.id.action_accueilFragment_to_listeDessertsFragment);
            }
        });
        binding.rvButtonsAccueil.setHasFixedSize(true);
        binding.rvButtonsAccueil.setAdapter(categorieAccueilAdapter);
        binding.rvButtonsAccueil.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}