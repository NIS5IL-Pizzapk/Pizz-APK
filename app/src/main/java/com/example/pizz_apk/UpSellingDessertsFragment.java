package com.example.pizz_apk;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pizz_apk.adapters.PlatUniqueListener;
import com.example.pizz_apk.databinding.FragmentUpSellingBoissonsBinding;
import com.example.pizz_apk.databinding.FragmentUpSellingDessertsBinding;
import com.example.pizz_apk.databinding.FragmentUpSellingSupplementsBinding;
import com.example.pizz_apk.models.RetroFitRequests;
import com.example.pizz_apk.viewmodels.ListeBoissonsViewModel;
import com.example.pizz_apk.viewmodels.PlatUniqueViewModel;


public class UpSellingDessertsFragment extends Fragment {

    FragmentUpSellingDessertsBinding binding;
    ListeBoissonsViewModel boissonsViewModel;
    PlatUniqueViewModel platUniqueViewModel;
    Context context = getContext();
    PlatUniqueListener listener;
    RetroFitRequests requests;

    public UpSellingDessertsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUpSellingDessertsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnSuivant.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_upSellingDessertsFragment_to_nav_panier);
        });

    }
}