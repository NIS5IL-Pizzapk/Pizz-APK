package com.example.pizz_apk;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pizz_apk.databinding.FragmentAjoutReservationBinding;
import com.example.pizz_apk.models.Reservation;
import com.example.pizz_apk.viewmodels.ReservationViewModel;

import java.sql.Date;
import java.sql.Time;

public class AjoutReservationFragment extends Fragment {

    FragmentAjoutReservationBinding binding;
    ReservationViewModel reservationViewModel;


    public AjoutReservationFragment() {
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
        binding = FragmentAjoutReservationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        reservationViewModel = new ViewModelProvider(requireActivity()).get(ReservationViewModel.class);

        binding.btnReserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //récupère le champ de la date et le convertit en date sql pour l'ajouter à la liste des réservations
                String date = binding.etDate.getText().toString();
                Date dateSql = Date.valueOf(date);
                //récupère le champ de l'heure et le convertit en time sql pour l'ajouter à la liste des réservations
                String heure = binding.etTime.getText().toString();
                Time heureSql = Time.valueOf(heure);
                //récupère le champ du nombre de personnes et le convertit en int pour l'ajouter à la liste des réservations
                String nbPersonnes = binding.etNbPersonnes.getText().toString();
                int nbPersonnesInt = Integer.parseInt(nbPersonnes);

                reservationViewModel.addReservation(new Reservation("ALDEBERT", "Arnaud", "06 06 06 06 06", nbPersonnesInt, dateSql, heureSql));
                Navigation.findNavController(v).navigate(R.id.action_ajoutReservationFragment_to_nav_reservation);


            }
        });
    }
}