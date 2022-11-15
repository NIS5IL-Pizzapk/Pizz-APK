package com.example.pizz_apk.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizz_apk.R;
import com.example.pizz_apk.databinding.RvItemRestaurantBinding;
import com.example.pizz_apk.models.Restaurant;

import java.util.List;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.RestaurantsViewHolder>{

    List<Restaurant> restaurantsList;
    Context context;
    RestaurantsListener listener;
    RvItemRestaurantBinding binding;

    public RestaurantsAdapter(List<Restaurant> restaurantsList, Context context, RestaurantsListener listener) {
        this.restaurantsList = restaurantsList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RestaurantsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_item_restaurant,parent,false);
        return new RestaurantsViewHolder(RvItemRestaurantBinding.bind(view));

    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantsViewHolder holder, int position) {
        final Restaurant restaurant = restaurantsList.get(position);
        holder.binding.btnChoixRestaurant.setOnClickListener(v -> listener.onRestaurantClicked(restaurant));
        holder.binding.btnChoixRestaurant.setText(restaurant.getNom());

    }

    @Override
    public int getItemCount() {
        return restaurantsList.size();
    }

    public static class RestaurantsViewHolder extends RecyclerView.ViewHolder{
        RvItemRestaurantBinding binding;
        public RestaurantsViewHolder(RvItemRestaurantBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }



}
