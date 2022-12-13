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

public class ListeDessertsAdapter extends RecyclerView.Adapter<ListeDessertsAdapter.ListeDessertsViewHolder>{

    ArrayList<PlatPropose> dessertsList = new ArrayList<>();
    RvItemPlatBinding binding;
    Context context;
    PlatUniqueListener listener;

    public ListeDessertsAdapter(ArrayList<PlatPropose> dessertsList, Context context, PlatUniqueListener listener) {
        this.dessertsList = dessertsList;
        this.context = context;
        this.listener = listener;
    }

    public void setDessertsListList(ArrayList<PlatPropose> dessertsList) {
        this.dessertsList = dessertsList;
        notifyDataSetChanged();



    }

    @NonNull
    @Override
    public ListeDessertsAdapter.ListeDessertsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvItemPlatBinding binding = RvItemPlatBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ListeDessertsAdapter.ListeDessertsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListeDessertsAdapter.ListeDessertsViewHolder holder, int position) {
        PlatPropose platPropose = dessertsList.get(position);
        holder.binding.tvNomPlat.setText(platPropose.getNom());
        holder.binding.tvDescriptionPlat.setText(platPropose.getDescription());
        holder.binding.imgPlatMini.setImageResource(platPropose.getImage());
        holder.binding.tvPrixPlat.setText(String.format(Locale.getDefault(),"%.2f",platPropose.getPrix())+"€");
        holder.binding.imgPlatMini.setOnClickListener(v -> listener.onPlatUniqueClicked(platPropose));
        holder.binding.imgbtnPlatAllergenes.setOnClickListener(v -> listener.onPlatUniqueAllergenesClicked(platPropose));
    }

    @Override
    public int getItemCount() {
        return dessertsList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public static class ListeDessertsViewHolder extends RecyclerView.ViewHolder {

        RvItemPlatBinding binding;

        public ListeDessertsViewHolder(@NonNull RvItemPlatBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
