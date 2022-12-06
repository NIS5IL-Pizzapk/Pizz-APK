package com.example.pizz_apk;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.pizz_apk.models.PlatPropose;
import com.example.pizz_apk.models.RetroFitResponse;
import com.example.pizz_apk.models.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentSignup extends Fragment {

    private EditText Username, Email, Password, ConfirmPassword;
    private Button BnRegister;

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
       Username = view.findViewById(R.id.input_name);
       Email = view.findViewById(R.id.input_email);
       Password = view.findViewById(R.id.input_password);

        BnRegister = view.findViewById(R.id.fragment_login_btn_signup);

        BnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performRegistration();
            }
        });

        return view;
    }

    public void performRegistration(){
        String username = Username.getText().toString();
        String email = Email.getText().toString();
        String password = Password.getText().toString();

        Call<User> call = MainActivity.RetroFitRequests.performRegistration(username, email, password);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(response.body().getResponse().equals("ok")){
                    MainActivity.prefConfig.displayToast("Votre compte a été créé avec succès");
                    Log.d("onResponse", "onResponse: " + response.body().getResponse());
                }
                else if(response.body().getResponse().equals("exist")){
                    MainActivity.prefConfig.displayToast("L'utilisateur existe déjà");
                    Log.d("onResponse", "onResponse: " + response.body().getResponse());
                }
                else if(response.body().getResponse().equals("error")){
                    MainActivity.prefConfig.displayToast("Quelque chose s'est mal passé");
                    Log.d("onResponse", "onResponse: " + response.body().getResponse());
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
           Log.d("onFailure", "onFailure: " + t.getMessage());
            }

        });
        Username.setText("");
        Email.setText("");
        Password.setText("");
    }

}