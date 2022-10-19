package com.example.pizz_apk.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizz_apk.databinding.ActivityListePizzasBinding;
import com.example.pizz_apk.databinding.RvItemPizzaBinding;
import com.example.pizz_apk.models.PlatPropose;

import java.util.ArrayList;
import java.util.List;

public class ListePizzasAdapter extends RecyclerView.Adapter<ListePizzasAdapter.ListePizzasViewHolder>{

    List<PlatPropose> pizzasList = new ArrayList<>();
    RvItemPizzaBinding binding;
    Context context;

    public ListePizzasAdapter(List<PlatPropose> pizzasList, Context context) {
        this.pizzasList = pizzasList;
        this.context = context;
    }

    public void setPizzasList(List<PlatPropose> pizzasList) {
        this.pizzasList.clear();
        this.pizzasList.addAll(pizzasList);
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListePizzasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = RvItemPizzaBinding.inflate(LayoutInflater.from(context));
        return new ListePizzasViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ListePizzasViewHolder holder, int position) {
        binding.tvNomPizza.setText(pizzasList.get(position).getNom());
        binding.tvDescriptionPizza.setText(pizzasList.get(position).getDescription());
        binding.tvPrixPizza.setText(String.format("%.2f",pizzasList.get(position).getPrix())+"€");
    }

    @Override
    public int getItemCount() {
        return pizzasList.size();
    }

    public static class ListePizzasViewHolder extends RecyclerView.ViewHolder{

        RvItemPizzaBinding binding;

        public ListePizzasViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
