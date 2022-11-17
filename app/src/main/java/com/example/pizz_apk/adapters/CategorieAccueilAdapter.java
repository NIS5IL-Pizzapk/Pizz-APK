package com.example.pizz_apk.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizz_apk.databinding.RvItemAccueilBinding;
import com.example.pizz_apk.models.CategorieAccueil;

import java.util.ArrayList;
import java.util.List;

public class CategorieAccueilAdapter extends RecyclerView.Adapter<CategorieAccueilAdapter.CategorieAccueilViewHolder> {
    List<CategorieAccueil> categorieList = new ArrayList<>();
    CategorieAccueilListener listener;
    RvItemAccueilBinding binding;
    Context context;

    public CategorieAccueilAdapter(List<CategorieAccueil> categorieList, Context context, CategorieAccueilListener categorieAccueilListener) {
        this.categorieList = categorieList;
        this.context = context;
        this.listener = categorieAccueilListener;
    }

    @NonNull
    @Override
    public CategorieAccueilViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = RvItemAccueilBinding.inflate(LayoutInflater.from(context));
        return new CategorieAccueilAdapter.CategorieAccueilViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull CategorieAccueilViewHolder holder, int position) {
        binding.imgAccueil.setImageResource(categorieList.get(position).getImage());
        final CategorieAccueil categorieAccueil = categorieList.get(position);
        binding.getRoot().setOnClickListener(v -> listener.onCategorieAccueilClicked(categorieAccueil));

    }

    @Override
    public int getItemCount() {
        return categorieList.size();

    }




    public static class CategorieAccueilViewHolder extends RecyclerView.ViewHolder{

        RvItemAccueilBinding binding;

        public CategorieAccueilViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
