package com.example.pizz_apk;

import android.content.Context;
import android.graphics.drawable.Drawable;
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
            binding.imgPlat.setImageDrawable(Drawable.createFromPath(String.valueOf(plat.getImage())));

            binding.btnPlatCommander.setOnClickListener(v -> {
                platUniqueViewModel.addPlatToPanier(plat);
                plat.setQuantite(1);
                //si le type du plat est 1 ou 2, on va vers la page upselling suppléments sinon on va direct à l'upselling boissons
                if (platUniqueViewModel.getSelectedTypePlat() == 1 || platUniqueViewModel.getSelectedTypePlat() == 2 || platUniqueViewModel.getSelectedTypePlat() == 3) {
                    Navigation.findNavController(v).navigate(R.id.action_platUniqueFragment_to_upSellingSupplementsFragment);
                } else {
                    Navigation.findNavController(v).navigate(R.id.action_platUniqueFragment_to_upSellingBoissonsFragment);
                }
            });
        });
    }
}