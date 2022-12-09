package com.example.pizz_apk;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pizz_apk.databinding.FragmentListePizzasBinding;
import com.example.pizz_apk.databinding.FragmentSignupBinding;
import com.example.pizz_apk.models.RetroFitRequests;
import com.example.pizz_apk.models.RetroFitResponse;
import com.example.pizz_apk.models.User;
import com.example.pizz_apk.services.Utils;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentSignup extends Fragment {
    FragmentSignupBinding binding;
    private EditText Username, Email, Password, ConfirmPassword;
    private Button BnRegister;
    Context ctx = getContext();
    RetroFitRequests<R> requests;

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

        binding = FragmentSignupBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
         }

    public void performRegistration(){
        HashMap<String, String> map = new HashMap<>();
        map.put("username", Username.getText().toString());
        map.put("email", Email.getText().toString());
        map.put("password", Password.getText().toString());
        Call<Void> call = requests.performRegistration();

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if(response.code() == 200){
                    Log.d("TAG", "onResponse: " + response.body().toString());
                    Toast.makeText(getContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                }else if(response.code() == 400){
                    Log.d("TAG", "onResponse: " + response.errorBody());
                    Toast.makeText(getContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
           Log.d("onFailure", "onFailure: " + t.getMessage());
           Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }

        });
        Username.setText("");
        Email.setText("");
        Password.setText("");
    }

}