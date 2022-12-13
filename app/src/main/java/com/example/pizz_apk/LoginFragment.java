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

import com.example.pizz_apk.models.LoginResult;
import com.example.pizz_apk.models.RetroFitRequests;
import com.example.pizz_apk.services.Utils;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginFragment extends Fragment {

    private Retrofit retrofit;
    RetroFitRequests requests;

    public LoginFragment() {
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
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view).show();

        Button loginButton = view.findViewById(R.id.fragment_login_btn_connexion);
        EditText email = view.findViewById(R.id.input_username);
        EditText password = view.findViewById(R.id.input_password);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> map = new HashMap<>();
                map.put("email", email.getText().toString());
                map.put("password", password.getText().toString());

                Call<LoginResult> call = requests.executeLogin(map);
                call.enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {

                        if (response.isSuccessful()) {
                          LoginResult result = response.body();
                          AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle(result.getUsername());
                            builder.setMessage(result.getMail());
                            builder.show();
                            Toast.makeText(getContext(), "Connexion réussie", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Connexion échouée", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResult> call, Throwable t) {
                        Toast.makeText(getContext(), "Erreur de connexion", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
