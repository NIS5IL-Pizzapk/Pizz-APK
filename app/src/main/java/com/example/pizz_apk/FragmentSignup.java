package com.example.pizz_apk;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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


        Button signupButton = view.findViewById(R.id.fragment_login_btn_connexion);
        EditText username = view.findViewById(R.id.input_name);
        EditText email = view.findViewById(R.id.input_email);
        EditText password = view.findViewById(R.id.input_password);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> map = new HashMap<>();
                map.put("username", username.getText().toString());
                map.put("email", email.getText().toString());
                map.put("password", password.getText().toString());
                requests.executeSignup(map).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getContext(), "Inscription réussie", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Inscription échouée", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getContext(), "Inscription échouée", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}