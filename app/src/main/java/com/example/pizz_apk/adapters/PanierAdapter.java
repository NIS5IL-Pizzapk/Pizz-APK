package com.example.pizz_apk.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizz_apk.R;
import com.example.pizz_apk.databinding.RvItemPanierBinding;
import com.example.pizz_apk.models.PlatPropose;

import java.util.List;

public class PanierAdapter extends RecyclerView.Adapter<PanierAdapter.PanierViewHolder>{
    LiveData<List<PlatPropose>> platsList = new MutableLiveData<>();
    RvItemPanierBinding binding;
    Context context;
    PanierListener listener;

    public PanierAdapter(LiveData<List<PlatPropose>> platsList, Context context, PanierListener listener) {
        this.platsList = platsList;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public PanierAdapter.PanierViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_item_panier,parent,false);
        return new PanierAdapter.PanierViewHolder(RvItemPanierBinding.bind(view));
    }

    @Override
    public void onBindViewHolder(@NonNull PanierAdapter.PanierViewHolder holder, int position) {
        final PlatPropose plat = platsList.getValue().get(position);
        holder.binding.tvNomItem.setText(plat.getNom());
        holder.binding.tvPrixItem.setText(String.valueOf(plat.getPrix())+ " €");
        holder.binding.tvNombreItemPanier.setText(String.valueOf(plat.getQuantite()));
        // ajoute un à la quantité du plat quand on clique sur le bouton + et met à jour le prix total du panier
        holder.binding.ivAddPanier.setOnClickListener(v -> {
            plat.setQuantite(plat.getQuantite()+1);
            holder.binding.tvNombreItemPanier.setText(String.valueOf(plat.getQuantite()));
            listener.onAddQuantity(plat);
        });
        // enlève un à la quantité du plat quand on clique sur le bouton - et met à jour le prix total du panier
        holder.binding.ivMoinsPanier.setOnClickListener(v -> {
            if (plat.getQuantite() > 1){
                plat.setQuantite(plat.getQuantite()-1);
                holder.binding.tvNombreItemPanier.setText(String.valueOf(plat.getQuantite()));
                listener.onRemoveQuantity(plat);
            }
        });
        // supprime le plat du panier quand on clique sur le bouton supprimer
        holder.binding.ivDeletePanier.setOnClickListener(v -> {
            listener.onRemovePlat(plat);
            notifyDataSetChanged();
        });

        //si le plat a des suppléments
        if (plat.getSupplementsactifs() != null) {
            //récupère le nom et la quantité de chaque suppléments du plat et les affiche
            String Supplements = "";
            for (int i = 0; i < plat.getSupplementsactifs().size(); i++) {
                Supplements += plat.getSupplementsactifs().get(i).getQuantite() + " ";
                Supplements += plat.getSupplementsactifs().get(i).getNom() + " ";
                String Newligne = System.getProperty("line.separator");
                Supplements += Newligne;
            }
            holder.binding.tvSuppItem.setText(Supplements);
        }


    }

    @Override
    public int getItemCount() {
        return platsList.getValue().size();
    }

    public static class PanierViewHolder extends RecyclerView.ViewHolder {

        RvItemPanierBinding binding;

        public PanierViewHolder(@NonNull RvItemPanierBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
