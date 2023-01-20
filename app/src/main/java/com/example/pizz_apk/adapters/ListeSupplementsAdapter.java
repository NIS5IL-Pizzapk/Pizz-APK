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

public class ListeSupplementsAdapter extends RecyclerView.Adapter<ListeSupplementsAdapter.ListeSupplementsViewHolder>{

    ArrayList<PlatPropose> supplementsList = new ArrayList<>();
    RvItemPlatBinding binding;
    Context context;
    PlatUniqueListener listener;

    public ListeSupplementsAdapter(ArrayList<PlatPropose> supplementsList, Context context, PlatUniqueListener listener) {
        this.supplementsList = supplementsList;
        this.context = context;
        this.listener = listener;
    }

    public void setSupplementsList(ArrayList<PlatPropose> supplementsList) {
        this.supplementsList = supplementsList;
        notifyDataSetChanged();


//        this.pizzasList = pizzasList;
//        this.notifyItemRangeRemoved(0,oldSize);
//        this.notifyItemRangeInserted(0,this.pizzasList.size());
    }

    @NonNull
    @Override
    public ListeSupplementsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvItemPlatBinding binding = RvItemPlatBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ListeSupplementsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListeSupplementsViewHolder holder, int position) {
        PlatPropose platPropose = supplementsList.get(position);
        holder.binding.tvNomPlat.setText(platPropose.getNom());
        holder.binding.tvDescriptionPlat.setText(platPropose.getDescription());
        holder.binding.tvPrixPlat.setText(String.format(Locale.getDefault(),"%.2f",platPropose.getPrix())+"€");
        holder.binding.btnDetailsPlat.setOnClickListener(v -> listener.onPlatUniqueClicked(platPropose));
        holder.binding.imgbtnPlatAllergenes.setOnClickListener(v -> listener.onPlatUniqueAllergenesClicked(platPropose));
    }

    @Override
    public int getItemCount() {
        return supplementsList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public static class ListeSupplementsViewHolder extends RecyclerView.ViewHolder {

        RvItemPlatBinding binding;

        public ListeSupplementsViewHolder(@NonNull RvItemPlatBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}