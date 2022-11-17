package com.example.pizz_apk;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pizz_apk.databinding.FragmentAllergenesBinding;
import com.example.pizz_apk.databinding.FragmentPlatUniqueBinding;
import com.example.pizz_apk.viewmodels.PlatUniqueViewModel;


public class AllergenesFragment extends Fragment {

    FragmentAllergenesBinding binding;

    public AllergenesFragment() {
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
        binding = FragmentAllergenesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PlatUniqueViewModel platUniqueViewModel = new ViewModelProvider(requireActivity()).get(PlatUniqueViewModel.class);
        platUniqueViewModel.getSelectedPlat().observe(getViewLifecycleOwner(), plat -> {

            binding.tvAller.setText(String.valueOf(plat.getListeAllergenes()));

        });
    }
}