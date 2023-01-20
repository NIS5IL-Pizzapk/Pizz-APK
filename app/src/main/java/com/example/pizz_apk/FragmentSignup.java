package com.example.pizz_apk;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
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


public class FragmentSignup extends Fragment {
    private Retrofit retrofit;
    RetroFitRequests requests;

    public FragmentSignup() {
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
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Button signupButton = view.findViewById(R.id.fragment_sign_btn_valid);
        EditText username = view.findViewById(R.id.input_name);
        EditText email = view.findViewById(R.id.input_email);
        EditText password = view.findViewById(R.id.input_password);
        EditText adress = view.findViewById(R.id.input_adresse_livraison);
        EditText telephone = view.findViewById(R.id.telephone);

        signupButton.setOnClickListener(v -> {

            HashMap<String, String> map = new HashMap<>();
            map.put("username", username.getText().toString());
            map.put("email", email.getText().toString());
            HashMap<String, String> mapadrss = new HashMap<>();
            mapadrss.put("adresse", adress.getText().toString());
            map.put("password", password.getText().toString());
            map.put("telephone", telephone.getText().toString());

            requests.executeSignup(map).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Log.d("error", "creation: " + response.code());
                    if (response.isSuccessful() && password != null) {
                        Toast.makeText(getContext(), "Inscription réussie", Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(v).navigate(R.id.loginFragment);
                    } else if (response.code() == 400) {
                        Log.d("error", "onResponse: " + response.code());
                        Toast.makeText(getContext(), "Inscription échouée", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.d("TAG", "onFailure: " + t.getMessage());
                    Toast.makeText(getContext(), "Inscription échouée", Toast.LENGTH_SHORT).show();
                }
            });


            requests.executeAddAdresse(mapadrss).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {

                        Log.d("adrss", "onResponse: " + response.body());
                        Toast.makeText(getContext(), "adress réussie", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("adrss", "onResponse: " + response.body());
                        Log.d("adrss", "onResponse: " + mapadrss);
                        Toast.makeText(getContext(), "adress échouée", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.d("adrss", "onResponse: " + t.getMessage());
                    Log.d("adrss", "onResponse: " + mapadrss);
                    Toast.makeText(getContext(), "Inscription échouée", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}