package com.example.pizz_apk;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
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
import com.example.pizz_apk.models.Allergene;
import com.example.pizz_apk.models.PlatPropose;
import com.example.pizz_apk.models.Reservation;
import com.example.pizz_apk.models.RetroFitRequests;
import com.example.pizz_apk.models.RetroFitResponse;
import com.example.pizz_apk.models.TestData;
import com.example.pizz_apk.services.Utils;
import com.example.pizz_apk.viewmodels.PlatUniqueViewModel;
import com.example.pizz_apk.viewmodels.ReservationViewModel;
import com.example.pizz_apk.viewmodels.UserViewModel;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReservationFragment extends Fragment {

    FragmentReservationBinding binding;
    ReservationViewModel reservationViewModel;
    List<Reservation> reservationList = new ArrayList<>();
    RetroFitRequests requests;




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
        this.requests = Utils.getRetrofitCon(requireContext());
        UserViewModel userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        int userId = userViewModel.getSelectedUserId();
        this.HandleGetReservations(userId);
        binding.btnReservationReserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_nav_reservation_to_ajoutReservationFragment);
            }
        });

    }

    public void HandleGetReservations(int userId) {

        Call<RetroFitResponse<ArrayList<Reservation>>> call =requests.getAllReservationsFromUser(userId);

        call.enqueue(new Callback<RetroFitResponse<ArrayList<Reservation>>>() {
            @Override
            public void onResponse(Call<RetroFitResponse<ArrayList<Reservation>>> call, Response<RetroFitResponse<ArrayList<Reservation>>> response) {
                if (response.isSuccessful()) {
                    String message = response.body().getMessage();
                    ArrayList<Reservation> result = response.body().getResult();

                    reservationViewModel = new ViewModelProvider(requireActivity()).get(ReservationViewModel.class);
                    reservationViewModel.setReservationList(result);
                    ReservationAdapter reservationAdapter = new ReservationAdapter(reservationViewModel.getReservationList(),getContext(), new ReservationListener() {
                        @Override
                        public void onRemoveReservation(Reservation reservation) {
                            this.HandleRemoveReservation(reservation,this);

                        }

                        private void HandleRemoveReservation(Reservation reservation, ReservationListener reservationListener) {
                            Call<RetroFitResponse<Reservation>> call = requests.deleteReservation(reservation.getId());
                            call.enqueue(new Callback<RetroFitResponse<Reservation>>() {
                                @Override
                                public void onResponse(Call<RetroFitResponse<Reservation>> call, Response<RetroFitResponse<Reservation>> response) {
                                    if (response.isSuccessful()) {
                                        String message = response.body().getMessage();
                                        Reservation result = response.body().getResult();
                                        reservationViewModel.removeReservation(result);
                                        binding.rvItemReservation.setAdapter(new ReservationAdapter(reservationViewModel.getReservationList(),getContext(),reservationListener));
                                    }
                                }

                                @Override
                                public void onFailure(Call<RetroFitResponse<Reservation>> call, Throwable t) {
                                    Log.e("ERROR", t.getMessage());
                                }
                            });

                        }
                    });


                    binding.rvItemReservation.setHasFixedSize(true);
                    binding.rvItemReservation.setAdapter(reservationAdapter);
                    binding.rvItemReservation.setLayoutManager(new LinearLayoutManager(getContext()));

                } else {
                    Utils.requestNotSuccessfulToast(requireContext(), response);
                }
            }
            @Override
            public void onFailure(Call<RetroFitResponse<ArrayList<Reservation>>> call, Throwable t) {
                Log.d("TAG", "onFailure: "+t.getMessage());
            }
        });
    }
}