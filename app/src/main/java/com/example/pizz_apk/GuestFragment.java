package com.example.pizz_apk;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pizz_apk.models.RetroFitRequests;
import com.example.pizz_apk.services.Utils;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GuestFragment extends Fragment {

    private Retrofit retrofit;
    RetroFitRequests requests;
    public Void poguid;

    public GuestFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.requests = Utils.getRetrofitCon(requireContext());

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_guest, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button guestButton = view.findViewById(R.id.fragment_login_btn_connexion);
        EditText username = view.findViewById(R.id.input_name);
        EditText email = view.findViewById(R.id.input_username);
        EditText phone = view.findViewById(R.id.input_password);

        guestButton.setOnClickListener(v -> {
            HashMap<String, String> map = new HashMap<>();
            map.put("nom", username.getText().toString());
            map.put("email", email.getText().toString());
            map.put("telephone", phone.getText().toString());
            this.requests.executeGuest(map).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Navigation.findNavController(v).navigate(R.id.accueilFragment);
                    } else {
                        Toast.makeText(requireContext(), "Erreur lors de l'inscription", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(requireContext(), "Erreur lors de l'inscription", Toast.LENGTH_SHORT).show();
                }
            });
        });

    }
}