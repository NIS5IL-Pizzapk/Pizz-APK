package com.example.pizz_apk.models;

import com.example.pizz_apk.R;
import com.example.pizz_apk.viewmodels.RestaurantsViewModel;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TestData {

    public static List<CategorieAccueil> ListeCatégorie = new ArrayList<CategorieAccueil>(Arrays.asList(
            new CategorieAccueil("Nos Pizzas Rouges",R.drawable.pizza,"pizzas_rouges"),
            new CategorieAccueil("Nos Pizzas Blanches",R.drawable.pizza_blanche,"pizzas_blanches"),
            new CategorieAccueil("Nos Burgers",R.drawable.burger,"burgers"),
            new CategorieAccueil("Nos Boissons",R.drawable.coca,"boissons"),
            new CategorieAccueil("Nos Desserts",R.drawable.dessert,"desserts")
    ));

    public static List<Restaurant> listeRestaurant = new ArrayList<Restaurant>(Arrays.asList(
            new Restaurant("Clarensac"),
            new Restaurant("Vauvert")
    ));

    public static List<Reservation> listeReservation = new ArrayList<Reservation>(Arrays.asList(
            new Reservation("ALDEBERT","Arnaud", "06 06 06 06 06", 3,new Date(),new Time(12,0,0))
    ));


}
