package com.example.pizz_apk.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizz_apk.databinding.RvItemUpsellingBinding;
import com.example.pizz_apk.models.PlatPropose;
import com.example.pizz_apk.viewmodels.PlatUniqueViewModel;

import java.util.ArrayList;
import java.util.Locale;

public class UpSellingBoissonsAdapter extends RecyclerView.Adapter<UpSellingBoissonsAdapter.UpSellingBoissonsViewHolder>{

    ArrayList<PlatPropose> boissonsList = new ArrayList<>();
    RvItemUpsellingBinding binding;
    Context context;
    UpSellingListener listener;

    public UpSellingBoissonsAdapter(ArrayList<PlatPropose> boissonsList, Context context, UpSellingListener listener) {
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
    public UpSellingBoissonsAdapter.UpSellingBoissonsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvItemUpsellingBinding binding = RvItemUpsellingBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new UpSellingBoissonsAdapter.UpSellingBoissonsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UpSellingBoissonsViewHolder holder, int position) {
        PlatPropose platPropose = boissonsList.get(position);
        holder.binding.tvNomPlat.setText(platPropose.getNom());
        holder.binding.tvDescriptionPlat.setText(platPropose.getDescription());
        holder.binding.imgPlatMini.setImageDrawable(Drawable.createFromPath(String.valueOf(platPropose.getImage())));
        holder.binding.tvPrixPlat.setText(String.format(Locale.getDefault(),"%.2f",platPropose.getPrix())+"€");
        holder.binding.tvNombreItemPanier.setText(String.valueOf(platPropose.getQuantite()));
        // ajoute un à la quantité du plat quand on clique sur le bouton + et met à jour le prix total du panier
        holder.binding.ivAddPanier.setOnClickListener(v -> {
            platPropose.setQuantite(platPropose.getQuantite()+1);
            holder.binding.tvNombreItemPanier.setText(String.valueOf(platPropose.getQuantite()));
            listener.onAddQuantity(platPropose);
        });
        // enlève un à la quantité du plat quand on clique sur le bouton - et met à jour le prix total du panier
        holder.binding.ivMoinsPanier.setOnClickListener(v -> {
            if (platPropose.getQuantite() > 1){
                platPropose.setQuantite(platPropose.getQuantite()-1);
                holder.binding.tvNombreItemPanier.setText(String.valueOf(platPropose.getQuantite()));
                listener.onRemoveQuantity(platPropose);
            }
        });
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


    public static class UpSellingBoissonsViewHolder extends RecyclerView.ViewHolder {

        RvItemUpsellingBinding binding;

        public UpSellingBoissonsViewHolder(@NonNull RvItemUpsellingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

