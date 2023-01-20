package com.example.pizz_apk.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;


import com.example.pizz_apk.PlatUniqueFragment;
import com.example.pizz_apk.R;
import com.example.pizz_apk.databinding.RvItemPlatBinding;
import com.example.pizz_apk.models.CategorieAccueil;
import com.example.pizz_apk.models.PlatPropose;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListePizzasAdapter extends RecyclerView.Adapter<ListePizzasAdapter.ListePizzasViewHolder>{

    ArrayList<PlatPropose> pizzasList = new ArrayList<>();
    RvItemPlatBinding binding;
    Context context;
    PlatUniqueListener listener;

    public ListePizzasAdapter(ArrayList<PlatPropose> pizzasList, Context context, PlatUniqueListener listener) {
        this.pizzasList = pizzasList;
        this.context = context;
        this.listener = listener;
    }

    public void setPizzasList(ArrayList<PlatPropose> pizzasList) {
        this.pizzasList = pizzasList;
        notifyDataSetChanged();


//        this.pizzasList = pizzasList;
//        this.notifyItemRangeRemoved(0,oldSize);
//        this.notifyItemRangeInserted(0,this.pizzasList.size());
    }

    @NonNull
    @Override
    public ListePizzasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvItemPlatBinding binding = RvItemPlatBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ListePizzasViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListePizzasViewHolder holder, int position) {
        PlatPropose platPropose = pizzasList.get(position);
        holder.binding.tvNomPlat.setText(platPropose.getNom());
        holder.binding.tvDescriptionPlat.setText(platPropose.getDescription());
        holder.binding.tvPrixPlat.setText(String.format(Locale.getDefault(),"%.2f",platPropose.getPrix())+"€");
        holder.binding.btnDetailsPlat.setOnClickListener(v -> listener.onPlatUniqueClicked(platPropose));
        holder.binding.imgbtnPlatAllergenes.setOnClickListener(v -> listener.onPlatUniqueAllergenesClicked(platPropose));
    }

    @Override
    public int getItemCount() {
        return pizzasList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public static class ListePizzasViewHolder extends RecyclerView.ViewHolder {

        RvItemPlatBinding binding;

        public ListePizzasViewHolder(@NonNull RvItemPlatBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}


