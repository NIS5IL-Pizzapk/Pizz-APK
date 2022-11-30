package com.example.pizz_apk.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizz_apk.databinding.RvItemPlatBinding;
import com.example.pizz_apk.models.PlatPropose;

import java.util.ArrayList;
import java.util.Locale;

public class ListeBurgersAdapter extends RecyclerView.Adapter<ListeBurgersAdapter.ListeBurgersViewHolder>{

    ArrayList<PlatPropose> burgersList = new ArrayList<>();
    RvItemPlatBinding binding;
    Context context;
    PlatUniqueListener listener;

    public ListeBurgersAdapter(ArrayList<PlatPropose> burgersList, Context context, PlatUniqueListener listener) {
        this.burgersList = burgersList;
        this.context = context;
        this.listener = listener;
    }

    public void setBurgersList(ArrayList<PlatPropose> burgersList) {
        this.burgersList = burgersList;
        notifyDataSetChanged();



    }

    @NonNull
    @Override
    public ListeBurgersAdapter.ListeBurgersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvItemPlatBinding binding = RvItemPlatBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ListeBurgersAdapter.ListeBurgersViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListeBurgersAdapter.ListeBurgersViewHolder holder, int position) {
        PlatPropose platPropose = burgersList.get(position);
        holder.binding.tvNomPlat.setText(platPropose.getNom());
        holder.binding.tvDescriptionPlat.setText(platPropose.getDescription());
        holder.binding.tvPrixPlat.setText(String.format(Locale.getDefault(),"%.2f",platPropose.getPrix())+"€");
        holder.binding.imgPlatMini.setOnClickListener(v -> listener.onPlatUniqueClicked(platPropose));
        holder.binding.imgbtnPlatAllergenes.setOnClickListener(v -> listener.onPlatUniqueAllergenesClicked(platPropose));
    }

    @Override
    public int getItemCount() {
        return burgersList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public static class ListeBurgersViewHolder extends RecyclerView.ViewHolder {

        RvItemPlatBinding binding;

        public ListeBurgersViewHolder(@NonNull RvItemPlatBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
