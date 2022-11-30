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
import com.example.pizz_apk.databinding.RvItemPizzaBinding;
import com.example.pizz_apk.models.CategorieAccueil;
import com.example.pizz_apk.models.PlatPropose;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListePizzasAdapter extends RecyclerView.Adapter<ListePizzasAdapter.ListePizzasViewHolder>{

    ArrayList<PlatPropose> pizzasList = new ArrayList<>();
    RvItemPizzaBinding binding;
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
        RvItemPizzaBinding binding = RvItemPizzaBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ListePizzasViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListePizzasViewHolder holder, int position) {
        PlatPropose platPropose = pizzasList.get(position);
        holder.binding.tvNomPizza.setText(platPropose.getNom());
        holder.binding.tvDescriptionPizza.setText(platPropose.getDescription());
        holder.binding.tvPrixPizza.setText(String.format(Locale.getDefault(),"%.2f",platPropose.getPrix())+"€");
        holder.binding.imgPizzaMini.setOnClickListener(v -> listener.onPlatUniqueClicked(platPropose));
        holder.binding.imgbtnPizzaAllergenes.setOnClickListener(v -> listener.onPlatUniqueAllergenesClicked(platPropose));
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

        RvItemPizzaBinding binding;

        public ListePizzasViewHolder(@NonNull RvItemPizzaBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}


