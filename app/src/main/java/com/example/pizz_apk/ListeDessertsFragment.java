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

import com.example.pizz_apk.adapters.ListeBoissonsAdapter;
import com.example.pizz_apk.adapters.ListeDessertsAdapter;
import com.example.pizz_apk.adapters.PlatUniqueListener;
import com.example.pizz_apk.databinding.FragmentListeBoissonsBinding;
import com.example.pizz_apk.databinding.FragmentListeDessertsBinding;
import com.example.pizz_apk.models.PlatPropose;
import com.example.pizz_apk.models.RetroFitRequests;
import com.example.pizz_apk.models.RetroFitResponse;
import com.example.pizz_apk.services.Utils;
import com.example.pizz_apk.viewmodels.ListeBoissonsViewModel;
import com.example.pizz_apk.viewmodels.ListeDessertsViewModel;
import com.example.pizz_apk.viewmodels.PlatUniqueViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListeDessertsFragment extends Fragment {

    FragmentListeDessertsBinding binding;
    ListeDessertsViewModel dessertsViewModel;
    PlatUniqueViewModel platUniqueViewModel;
    Context context = getContext();
    PlatUniqueListener listener;
    RetroFitRequests requests;

    public ListeDessertsFragment() {
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
        binding = FragmentListeDessertsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.requests = Utils.getRetrofitCon(requireContext());
        this.HandleGetDesserts(view);



    }

    public void HandleGetDesserts(View view){
        Call<RetroFitResponse<ArrayList<PlatPropose>>> call =requests.getPlats();

        call.enqueue(new Callback<RetroFitResponse<ArrayList<PlatPropose>>>() {
            @Override
            public void onResponse(Call<RetroFitResponse<ArrayList<PlatPropose>>> call, Response<RetroFitResponse<ArrayList<PlatPropose>>> response) {
                if (response.isSuccessful()) {
                    String message = response.body().getMessage();
                    ArrayList<PlatPropose> result = response.body().getResult();
                    //trier les plats pour ne garder que les Desserts
                    ArrayList<PlatPropose> Desserts = new ArrayList<>();
                    for (PlatPropose plat : result) {
                        if (plat.getType().equals("dessert")) {
                            Desserts.add(plat);
                        }
                    }

                    dessertsViewModel = new ViewModelProvider(requireActivity()).get(ListeDessertsViewModel.class);
                    dessertsViewModel.setListDessertsLiveData(Desserts);
                    platUniqueViewModel = new ViewModelProvider(requireActivity()).get(PlatUniqueViewModel.class);
                    ListeDessertsAdapter adapter = new ListeDessertsAdapter(dessertsViewModel.getListDessertsLiveData().getValue(), context, new PlatUniqueListener() {
                        @Override
                        public void onPlatUniqueClicked(PlatPropose platPropose) {
                            platUniqueViewModel.setSelectedPlat(platPropose);
                            Navigation.findNavController(view).navigate(R.id.action_listeDessertsFragment_to_platUniqueFragment);
                        }

                        @Override
                        public void onPlatUniqueAllergenesClicked(PlatPropose platPropose) {
                            platUniqueViewModel.setSelectedPlat(platPropose);
                            Navigation.findNavController(view).navigate(R.id.action_listeDessertsFragment_to_allergenesFragment);
                        }
                    });
                    binding.rvDessertsListe.setHasFixedSize(true);
                    binding.rvDessertsListe.setAdapter(adapter);
                    binding.rvDessertsListe.setLayoutManager(new LinearLayoutManager(context));

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