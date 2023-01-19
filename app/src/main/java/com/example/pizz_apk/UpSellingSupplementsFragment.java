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

import com.example.pizz_apk.adapters.PlatUniqueListener;
import com.example.pizz_apk.adapters.UpSellingDessertsAdapter;
import com.example.pizz_apk.adapters.UpSellingListener;
import com.example.pizz_apk.adapters.UpSellingSupplementsAdapter;
import com.example.pizz_apk.databinding.FragmentListeBoissonsBinding;
import com.example.pizz_apk.databinding.FragmentPlatUniqueBinding;
import com.example.pizz_apk.databinding.FragmentUpSellingSupplementsBinding;
import com.example.pizz_apk.models.PlatPropose;
import com.example.pizz_apk.models.RetroFitRequests;
import com.example.pizz_apk.models.RetroFitResponse;
import com.example.pizz_apk.services.Utils;
import com.example.pizz_apk.viewmodels.ListeBoissonsViewModel;
import com.example.pizz_apk.viewmodels.ListeDessertsViewModel;
import com.example.pizz_apk.viewmodels.ListeSupplementsViewModel;
import com.example.pizz_apk.viewmodels.PlatUniqueViewModel;
import com.example.pizz_apk.viewmodels.RestaurantsViewModel;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UpSellingSupplementsFragment extends Fragment {

    FragmentUpSellingSupplementsBinding binding;
    ListeSupplementsViewModel supplementsViewModel;
    PlatUniqueViewModel platUniqueViewModel;
    RestaurantsViewModel restaurantsViewModel;
    Context context = getContext();
    PlatUniqueListener listener;
    RetroFitRequests requests;

    public UpSellingSupplementsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUpSellingSupplementsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.requests = Utils.getRetrofitCon(requireContext());
        restaurantsViewModel = new ViewModelProvider(requireActivity()).get(RestaurantsViewModel.class);
        int idRestaurant = restaurantsViewModel.getSelectedRestaurant().getValue().getId();
        this.HandleGetSupplements(view,idRestaurant,6);
        binding.btnSuivant.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_upSellingSupplementsFragment_to_upSellingBoissonsFragment);
        });

        binding.imageView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_upSellingSupplementsFragment_to_accueilFragment);
            }
        });

    }
    public void HandleGetSupplements(View view, int restaurantId, int typeId){
        HashMap<String, Integer> map = new HashMap<>();
        map.put("restaurantId",restaurantId);
        map.put("typeId", typeId);

        Call<RetroFitResponse<ArrayList<PlatPropose>>> call =requests.getSupplementsByTypeEtRestaurant(map);

        call.enqueue(new Callback<RetroFitResponse<ArrayList<PlatPropose>>>() {
            @Override
            public void onResponse(Call<RetroFitResponse<ArrayList<PlatPropose>>> call, Response<RetroFitResponse<ArrayList<PlatPropose>>> response) {
                if (response.isSuccessful()) {
                    String message = response.body().getMessage();
                    ArrayList<PlatPropose> result = response.body().getResult();


                    supplementsViewModel = new ViewModelProvider(requireActivity()).get(ListeSupplementsViewModel.class);
                    supplementsViewModel.setListSupplementsLiveData(result);
                    platUniqueViewModel = new ViewModelProvider(requireActivity()).get(PlatUniqueViewModel.class);
                    UpSellingSupplementsAdapter adapter = new UpSellingSupplementsAdapter(supplementsViewModel.getListSupplementsLiveData().getValue(), context, new UpSellingListener() {
                        @Override
                        public void onAddQuantity(PlatPropose platPropose) {
                            //recupère le plat selectionné et ajoute ce supplement à la liste des supplements du plat
                            PlatPropose plat = platUniqueViewModel.getSelectedPlat().getValue();
                            plat.addSupplementActif(platPropose);

                        }

                        @Override
                        public void onRemoveQuantity(PlatPropose platPropose) {
                            //si la quantité du plat est à 0, on le retire de la liste des supplements du plat
                            if (platPropose.getQuantite() == 0) {
                                PlatPropose plat = platUniqueViewModel.getSelectedPlat().getValue();
                                plat.removeSupplementActif(platPropose);
                            }

                        }

                    });
                    binding.rvSuppListe.setHasFixedSize(true);
                    binding.rvSuppListe.setAdapter(adapter);
                    binding.rvSuppListe.setLayoutManager(new LinearLayoutManager(context));

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