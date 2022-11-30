package com.example.pizz_apk;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pizz_apk.R;
import com.example.pizz_apk.adapters.PlatUniqueListener;
import com.example.pizz_apk.databinding.FragmentListeBurgersBinding;
import com.example.pizz_apk.databinding.FragmentListePizzasBinding;
import com.example.pizz_apk.models.RetroFitRequests;
import com.example.pizz_apk.viewmodels.ListeBurgersViewModel;
import com.example.pizz_apk.viewmodels.ListePizzasViewModel;
import com.example.pizz_apk.viewmodels.PlatUniqueViewModel;

public class ListeBurgersFragment extends Fragment {

    FragmentListeBurgersBinding binding;
    ListeBurgersViewModel burgersViewModel;
    PlatUniqueViewModel platUniqueViewModel;
    Context context = getContext();
    PlatUniqueListener listener;
    RetroFitRequests requests;

    public ListeBurgersFragment() {
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
        binding = FragmentListeBurgersBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}