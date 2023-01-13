package com.example.pizz_apk.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizz_apk.R;
import com.example.pizz_apk.databinding.RvItemPlatBinding;
import com.example.pizz_apk.models.PlatPropose;

import java.util.ArrayList;
import java.util.Locale;

public class ListeBoissonsAdapter extends RecyclerView.Adapter<ListeBoissonsAdapter.ListeBoissonsViewHolder>{

    ArrayList<PlatPropose> boissonsList = new ArrayList<>();
    RvItemPlatBinding binding;
    Context context;
    PlatUniqueListener listener;

    public ListeBoissonsAdapter(ArrayList<PlatPropose> boissonsList, Context context, PlatUniqueListener listener) {
        this.boissonsList = boissonsList;
        this.context = context;
        this.listener = listener;
    }

    public void setBoissonsList(ArrayList<PlatPropose> boissonsList) {
        this.boissonsList = boissonsList;
        notifyDataSetChanged();



    }

    @NonNull
    @Override
    public ListeBoissonsAdapter.ListeBoissonsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvItemPlatBinding binding = RvItemPlatBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ListeBoissonsAdapter.ListeBoissonsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListeBoissonsViewHolder holder, int position) {
        PlatPropose platPropose = boissonsList.get(position);
        holder.binding.tvNomPlat.setText(platPropose.getNom());
        holder.binding.tvDescriptionPlat.setText(platPropose.getDescription());
        holder.binding.imgPlatMini.setImageResource(R.drawable.drink);
        holder.binding.tvPrixPlat.setText(String.format(Locale.getDefault(),"%.2f",platPropose.getPrix())+"€");
        holder.binding.imgPlatMini.setOnClickListener(v -> listener.onPlatUniqueClicked(platPropose));
        holder.binding.imgbtnPlatAllergenes.setOnClickListener(v -> listener.onPlatUniqueAllergenesClicked(platPropose));
    }

    @Override
    public int getItemCount() {
        return boissonsList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public static class ListeBoissonsViewHolder extends RecyclerView.ViewHolder {

        RvItemPlatBinding binding;

        public ListeBoissonsViewHolder(@NonNull RvItemPlatBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
