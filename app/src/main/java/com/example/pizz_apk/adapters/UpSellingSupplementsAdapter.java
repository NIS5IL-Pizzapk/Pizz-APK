package com.example.pizz_apk.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizz_apk.R;
import com.example.pizz_apk.databinding.RvItemUpsellingBinding;
import com.example.pizz_apk.models.PlatPropose;

import java.util.ArrayList;
import java.util.Locale;

public class UpSellingSupplementsAdapter extends RecyclerView.Adapter<UpSellingSupplementsAdapter.UpSellingSupplementsViewHolder>{

    ArrayList<PlatPropose> supplementsList = new ArrayList<>();
    RvItemUpsellingBinding binding;
    Context context;
    UpSellingListener listener;

    public UpSellingSupplementsAdapter(ArrayList<PlatPropose> supplementsList, Context context, UpSellingListener listener) {
        this.supplementsList = supplementsList;
        this.context = context;
        this.listener = listener;
    }

    public void setSupplementsList(ArrayList<PlatPropose> supplementsList) {
        this.supplementsList = supplementsList;
        notifyDataSetChanged();



    }

    @NonNull
    @Override
    public UpSellingSupplementsAdapter.UpSellingSupplementsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvItemUpsellingBinding binding = RvItemUpsellingBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new UpSellingSupplementsAdapter.UpSellingSupplementsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UpSellingSupplementsViewHolder holder, int position) {
        PlatPropose platPropose = supplementsList.get(position);
        holder.binding.tvNomPlat.setText(platPropose.getNom());
        holder.binding.tvDescriptionPlat.setText(platPropose.getDescription());
        holder.binding.imgPlatMini.setImageResource(R.drawable.supp);
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
            if (platPropose.getQuantite() > 0){
                platPropose.setQuantite(platPropose.getQuantite()-1);
                holder.binding.tvNombreItemPanier.setText(String.valueOf(platPropose.getQuantite()));

            }
            listener.onRemoveQuantity(platPropose);
        });
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


    public static class UpSellingSupplementsViewHolder extends RecyclerView.ViewHolder {

        RvItemUpsellingBinding binding;

        public UpSellingSupplementsViewHolder(@NonNull RvItemUpsellingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
