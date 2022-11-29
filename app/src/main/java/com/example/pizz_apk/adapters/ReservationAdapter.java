package com.example.pizz_apk.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizz_apk.R;
import com.example.pizz_apk.databinding.RvItemPanierBinding;
import com.example.pizz_apk.databinding.RvItemReservationBinding;
import com.example.pizz_apk.models.PlatPropose;
import com.example.pizz_apk.models.Reservation;

import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder>{
    LiveData<List<Reservation>> reservationList = new MutableLiveData<>();
    RvItemReservationBinding binding;
    Context context;
    ReservationListener listener;

    public ReservationAdapter(LiveData<List<Reservation>> reservationList, Context context, ReservationListener listener) {
        this.reservationList = reservationList;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public ReservationAdapter.ReservationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_item_reservation,parent,false);
        return new ReservationAdapter.ReservationViewHolder(RvItemReservationBinding.bind(view));
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationAdapter.ReservationViewHolder holder, int position) {
        final Reservation reservation = reservationList.getValue().get(position);
        holder.binding.tvDateItem.setText(String.valueOf(reservation.getDate()));
        holder.binding.tvHeureItem.setText(String.valueOf(reservation.getHeure()));
        holder.binding.tvNbpersonnesItem.setText(String.valueOf(reservation.getNbPersonnes())+ " personnes");

        // supprime la réservation quand on clique sur le bouton supprimer
        holder.binding.ivDeletePanier.setOnClickListener(v -> {
            listener.onRemoveReservation(reservation);
            notifyDataSetChanged();
        });



    }

    @Override
    public int getItemCount() {
        return reservationList.getValue().size();
    }

    public static class ReservationViewHolder extends RecyclerView.ViewHolder {

        RvItemReservationBinding binding;

        public ReservationViewHolder(@NonNull RvItemReservationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
