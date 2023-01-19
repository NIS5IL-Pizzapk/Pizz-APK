package com.example.pizz_apk;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pizz_apk.adapters.RestaurantsAdapter;
import com.example.pizz_apk.databinding.FragmentRestaurantChoixBinding;
import com.example.pizz_apk.models.Restaurant;
import com.example.pizz_apk.models.RetroFitRequests;
import com.example.pizz_apk.models.RetroFitResponse;
import com.example.pizz_apk.services.Utils;
import com.example.pizz_apk.viewmodels.RestaurantsViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RestaurantChoixFragment extends Fragment {

    private String[] listScrollingText;

    List<Restaurant> restaurantsList = new ArrayList<>();

    FragmentRestaurantChoixBinding binding;

    RestaurantsViewModel restaurantsViewModel;

    RetroFitRequests requests;


    TextView tv;

    public RestaurantChoixFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRestaurantChoixBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }
    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.requests = Utils.getRetrofitCon(requireContext());
        this.HandleGetRestaurants(view);

        binding.scrollText.setSelected(true);
        //Récupération de la liste des textes à faire défiler
        listScrollingText = getResources().getStringArray(R.array.scrolling_text);

        int randomIndex = new Random().nextInt(listScrollingText.length);
        String randomName = listScrollingText[randomIndex];
        binding.scrollText.setText(randomName);
    }

    public void HandleGetRestaurants(View view){

        Call<RetroFitResponse<ArrayList<Restaurant>>> call =requests.getAllRestaurants();



        call.enqueue(new Callback<RetroFitResponse<ArrayList<Restaurant>>>() {
            @Override
            public void onResponse(Call<RetroFitResponse<ArrayList<Restaurant>>> call, Response<RetroFitResponse<ArrayList<Restaurant>>> response) {
                if (response.isSuccessful()) {
                    String message = response.body().getMessage();
                    ArrayList<Restaurant> result = response.body().getResult();

                    restaurantsViewModel = new ViewModelProvider(requireActivity()).get(RestaurantsViewModel.class);
                    restaurantsViewModel.setListRestaurantLiveData(result);
                    RestaurantsAdapter restaurantsAdapter = new RestaurantsAdapter(restaurantsViewModel.getListRestaurantLiveData(),getContext(), restaurant -> {
                        restaurantsViewModel.setSelectedRestaurant(restaurant);
                        Navigation.findNavController(view).navigate(R.id.action_restaurantChoixFragment_to_accueilFragment);

                    });
                    binding.rvHomepageRestaurants.setHasFixedSize(true);
                    binding.rvHomepageRestaurants.setAdapter(restaurantsAdapter);
                    binding.rvHomepageRestaurants.setLayoutManager(new LinearLayoutManager(getContext()));

                } else {
                    Utils.requestNotSuccessfulToast(requireContext(), response);
                }
            }
            @Override
            public void onFailure(Call<RetroFitResponse<ArrayList<Restaurant>>> call, Throwable t) {
                Log.d("TAG", "onFailure: "+t.getMessage());
            }
        });
    }
}