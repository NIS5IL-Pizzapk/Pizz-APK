package com.example.pizz_apk.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizz_apk.databinding.FragmentListePizzasBinding;
import com.example.pizz_apk.databinding.RvItemPizzaBinding;
import com.example.pizz_apk.models.PlatPropose;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListePizzasAdapter extends RecyclerView.Adapter<ListePizzasAdapter.ListePizzasViewHolder>{

    List<PlatPropose> pizzasList = new ArrayList<>();
    RvItemPizzaBinding binding;
    Context context;

    public ListePizzasAdapter(List<PlatPropose> pizzasList, Context context) {
        this.pizzasList = pizzasList;
        this.context = context;
    }

    public void setPizzasList(int oldSize, List<PlatPropose> pizzasList) {
        this.pizzasList = pizzasList;
        this.notifyItemRangeRemoved(0,oldSize);
        this.notifyItemRangeInserted(0,this.pizzasList.size());
    }

    @NonNull
    @Override
    public ListePizzasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = RvItemPizzaBinding.inflate(LayoutInflater.from(context));
        return new ListePizzasViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ListePizzasViewHolder holder, int position) {
        holder.binding.tvNomPizza.setText(pizzasList.get(position).getNom());
        holder.binding.tvDescriptionPizza.setText(pizzasList.get(position).getDescription());
        holder.binding.tvPrixPizza.setText(String.format(Locale.getDefault(),"%.2f",pizzasList.get(position).getPrix())+"€");
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

    public static class ListePizzasViewHolder extends RecyclerView.ViewHolder{

        RvItemPizzaBinding binding;

        public ListePizzasViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = RvItemPizzaBinding.bind(itemView);
        }
    }

}
