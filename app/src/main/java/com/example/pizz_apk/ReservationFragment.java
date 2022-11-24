package com.example.pizz_apk;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pizz_apk.adapters.ListePizzasAdapter;
import com.example.pizz_apk.adapters.PanierAdapter;
import com.example.pizz_apk.adapters.PanierListener;
import com.example.pizz_apk.adapters.PlatUniqueListener;
import com.example.pizz_apk.adapters.ReservationAdapter;
import com.example.pizz_apk.adapters.ReservationListener;
import com.example.pizz_apk.databinding.FragmentPanierBinding;
import com.example.pizz_apk.databinding.FragmentReservationBinding;
import com.example.pizz_apk.models.PlatPropose;
import com.example.pizz_apk.models.Reservation;
import com.example.pizz_apk.models.TestData;
import com.example.pizz_apk.viewmodels.PlatUniqueViewModel;
import com.example.pizz_apk.viewmodels.ReservationViewModel;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ReservationFragment extends Fragment {

    FragmentReservationBinding binding;
    ReservationListener listener;
    ReservationViewModel reservationViewModel;
    List<Reservation> reservationList = new ArrayList<>();



    public ReservationFragment() {
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
        binding = FragmentReservationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        reservationList = TestData.listeReservation;
        reservationViewModel = new ViewModelProvider(requireActivity()).get(ReservationViewModel.class);
        reservationViewModel.setReservationList(reservationList);
        ReservationAdapter reservationAdapter = new ReservationAdapter(reservationViewModel.getReservationList(),getContext(), new ReservationListener() {
            @Override
            public void onRemoveReservation(Reservation reservation) {
                reservationViewModel.removeReservation(reservation);
            }
        });

        this.binding.rvItemReservation.setAdapter(reservationAdapter);
        this.binding.rvItemReservation.setLayoutManager(new LinearLayoutManager(getContext()));
        this.binding.rvItemReservation.setHasFixedSize(true);
    }
}