package com.example.pizz_apk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pizz_apk.adapters.ListePizzasAdapter;
import com.example.pizz_apk.adapters.PanierAdapter;
import com.example.pizz_apk.adapters.PanierListener;
import com.example.pizz_apk.adapters.PlatUniqueListener;
import com.example.pizz_apk.databinding.FragmentPanierBinding;
import com.example.pizz_apk.models.PlatPropose;
import com.example.pizz_apk.viewmodels.PlatUniqueViewModel;

import jp.wasabeef.blurry.Blurry;

public class PanierFragment extends Fragment {

    FragmentPanierBinding binding;
    PlatUniqueListener listener;
    PlatUniqueViewModel platUniqueViewModel;
    ListePizzasAdapter adapter;
    ConstraintLayout constraintLayout;
    Button btnCommander;

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
        ConstraintLayout constraintLayout = view.findViewById(R.id.popup);
        constraintLayout.setVisibility(View.GONE);
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

        binding.btnPanierCommander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.popup.setVisibility(View.VISIBLE);
                Blurry.with(requireContext()).radius(25).sampling(45).async().onto(constraintLayout);
            }
        });

        binding.buttonClosePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.popup.setVisibility(View.INVISIBLE);

            }
        });

        binding.btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_nav_panier_to_loginFragment);
            }
        });

        binding.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_nav_panier_to_fragmentSignup);
            }
        });

        binding.btnGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_nav_panier_to_guestFragment);
            }
        });



        this.binding.rvItemPanier.setAdapter(panierAdapter);
        this.binding.rvItemPanier.setLayoutManager(new LinearLayoutManager(getContext()));
        this.binding.rvItemPanier.setHasFixedSize(true);
    }
}