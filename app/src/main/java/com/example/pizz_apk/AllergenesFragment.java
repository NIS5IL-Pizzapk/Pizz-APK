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

import com.example.pizz_apk.adapters.ListeBurgersAdapter;
import com.example.pizz_apk.adapters.PlatUniqueListener;
import com.example.pizz_apk.databinding.FragmentAllergenesBinding;
import com.example.pizz_apk.databinding.FragmentPlatUniqueBinding;
import com.example.pizz_apk.models.Allergene;
import com.example.pizz_apk.models.PlatPropose;
import com.example.pizz_apk.models.RetroFitRequests;
import com.example.pizz_apk.models.RetroFitResponse;
import com.example.pizz_apk.services.Utils;
import com.example.pizz_apk.viewmodels.ListeBurgersViewModel;
import com.example.pizz_apk.viewmodels.PlatUniqueViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AllergenesFragment extends Fragment {

    FragmentAllergenesBinding binding;
    Context context = getContext();
    PlatUniqueViewModel platUniqueViewModel;
    PlatUniqueListener listener;
    RetroFitRequests requests;

    public AllergenesFragment() {
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
        binding = FragmentAllergenesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.requests = Utils.getRetrofitCon(requireContext());
        PlatUniqueViewModel platUniqueViewModel = new ViewModelProvider(requireActivity()).get(PlatUniqueViewModel.class);
        int idPlat = platUniqueViewModel.getSelectedPlat().getValue().getId();
        this.HandleGetAllergenes(idPlat);

    }

    public void HandleGetAllergenes(int idPlat) {

        Call<RetroFitResponse<ArrayList<Allergene>>> call =requests.getAllergeneByProduitId(idPlat);

        call.enqueue(new Callback<RetroFitResponse<ArrayList<Allergene>>>() {
            @Override
            public void onResponse(Call<RetroFitResponse<ArrayList<Allergene>>> call, Response<RetroFitResponse<ArrayList<Allergene>>> response) {
                if (response.isSuccessful()) {
                    String message = response.body().getMessage();
                    ArrayList<Allergene> result = response.body().getResult();

                    //récupère la réponse du serveur et affiche les allergènes
                    //si le plat ne contient pas d'allergènes, affiche "Ce plat ne contient pas d'allergènes"
                    if (result.size() == 0) {
                        binding.tvAller.setText("Ce plat ne contient pas d'allergènes");
                    } else {
                        String allergenes = "";
                        for (int i = 0; i < result.size(); i++) {
                            allergenes += result.get(i).getNom() + ", ";
                        }
                        binding.tvAller.setText(allergenes);
                    }

                } else {
                    Utils.requestNotSuccessfulToast(requireContext(), response);
                }
            }
            @Override
            public void onFailure(Call<RetroFitResponse<ArrayList<Allergene>>> call, Throwable t) {
                Log.d("TAG", "onFailure: "+t.getMessage());
            }
        });
    }
}