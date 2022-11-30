package com.example.pizz_apk;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pizz_apk.adapters.ListePizzasAdapter;
import com.example.pizz_apk.adapters.PlatUniqueListener;
import com.example.pizz_apk.databinding.FragmentListePizzasBinding;
import com.example.pizz_apk.models.PlatPropose;
import com.example.pizz_apk.models.RetroFitRequests;
import com.example.pizz_apk.models.RetroFitResponse;
import com.example.pizz_apk.services.Utils;
import com.example.pizz_apk.viewmodels.ListePizzasViewModel;
import com.example.pizz_apk.viewmodels.PlatUniqueViewModel;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class ListePizzasFragment extends Fragment {

    FragmentListePizzasBinding binding;
    ListePizzasViewModel pizzaListViewModel;
    PlatUniqueViewModel platUniqueViewModel;
    Context context = getContext();
    PlatUniqueListener listener;
    RetroFitRequests requests;

    public ListePizzasFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentListePizzasBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.requests = Utils.getRetrofitCon(requireContext());
        this.HandleGetPizzas(view);



    }

    public void HandleGetPizzas(View view){
        Call<RetroFitResponse<ArrayList<PlatPropose>>> call =requests.getPlats();

        call.enqueue(new Callback<RetroFitResponse<ArrayList<PlatPropose>>>() {
            @Override
            public void onResponse(Call<RetroFitResponse<ArrayList<PlatPropose>>> call, Response<RetroFitResponse<ArrayList<PlatPropose>>> response) {
                if (response.isSuccessful()) {
                    String message = response.body().getMessage();
                    ArrayList<PlatPropose> result = response.body().getResult();
                    //trier les pizzas pour afficher seulement les pizzas type pizza_rouge
                    ArrayList<PlatPropose> pizzasRouges = new ArrayList<>();
                    for (PlatPropose plat : result) {
                        if (plat.getType().equals("pizza_rouge")) {
                            pizzasRouges.add(plat);
                        }
                    }

                    ArrayList<PlatPropose> pizzasBlanches = new ArrayList<>();
                    for (PlatPropose plat : result) {
                        if (plat.getType().equals("pizza_blanche")) {
                            pizzasBlanches.add(plat);
                        }
                    }


                    pizzaListViewModel = new ViewModelProvider(requireActivity()).get(ListePizzasViewModel.class);
                    pizzaListViewModel.setListPizzaLiveData(pizzasRouges);
                    platUniqueViewModel = new ViewModelProvider(requireActivity()).get(PlatUniqueViewModel.class);
                    ListePizzasAdapter adapter = new ListePizzasAdapter(pizzaListViewModel.getListPizzaLiveData().getValue(), context, new PlatUniqueListener() {
                        @Override
                        public void onPlatUniqueClicked(PlatPropose platPropose) {
                            platUniqueViewModel.setSelectedPlat(platPropose);
                            Navigation.findNavController(view).navigate(R.id.action_listePizzasFragment_to_platUniqueFragment);
                        }

                        @Override
                        public void onPlatUniqueAllergenesClicked(PlatPropose platPropose) {
                            platUniqueViewModel.setSelectedPlat(platPropose);
                            Navigation.findNavController(view).navigate(R.id.action_listePizzasFragment_to_allergenesFragment);
                        }
                    });
                    binding.rvPizzasListe.setHasFixedSize(true);
                    binding.rvPizzasListe.setAdapter(adapter);
                    binding.rvPizzasListe.setLayoutManager(new LinearLayoutManager(context));

                    //NE RIEN TOUCHER A PARTIR D'ICI
                    pizzaListViewModel.getListPizzaLiveData().observe(getViewLifecycleOwner(), pizzasObserve -> {
                        adapter.setPizzasList(pizzasObserve);
                    });
                    binding.btnBaseTomate.setOnClickListener(view1 -> {
                        binding.btnBaseTomate.setEnabled(false);
                        binding.btnBaseCreme.setEnabled(true);
                        pizzaListViewModel.setListPizzaLiveData(pizzasRouges);
                    });
                    binding.btnBaseCreme.setOnClickListener(view1 -> {
                        binding.btnBaseTomate.setEnabled(true);
                        binding.btnBaseCreme.setEnabled(false);
                        pizzaListViewModel.setListPizzaLiveData(pizzasBlanches);
                    });
                    if(!pizzaListViewModel.isBaseCreme()){
                        binding.btnBaseTomate.callOnClick();
                    } else {
                        binding.btnBaseCreme.callOnClick();
                    }
                } else {
                    Utils.requestNotSuccessfulToast(requireContext(), response);
                }
            }
            @Override
            public void onFailure(Call<RetroFitResponse<ArrayList<PlatPropose>>> call, Throwable t) {
                Log.d("TAG", "onFailure: "+t.getMessage());
            }
        });
    }
}