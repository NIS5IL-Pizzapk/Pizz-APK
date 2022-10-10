package com.example.pizz_apk.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizz_apk.R;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.RestaurantsViewHolder>{

    List<String> restaurantsList;
    Context context;

    public RestaurantsAdapter(List<String> restaurantsList, Context context) {
        this.restaurantsList = restaurantsList;
        this.context = context;
    }

    @NonNull
    @Override
    public RestaurantsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_item_restaurant,parent,false);
        return new RestaurantsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantsViewHolder holder, int position) {
        holder.btnResto.setText(restaurantsList.get(position));
    }

    @Override
    public int getItemCount() {
        return restaurantsList.size();
    }

    public static class RestaurantsViewHolder extends RecyclerView.ViewHolder{
        Button btnResto;
        public RestaurantsViewHolder(@NonNull View itemView) {
            super(itemView);
            btnResto = itemView.findViewById(R.id.btn_choix_restaurant);
        }
    }



}
