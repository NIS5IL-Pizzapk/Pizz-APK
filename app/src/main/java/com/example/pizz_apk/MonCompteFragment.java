package com.example.pizz_apk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.example.pizz_apk.models.RetroFitRequests;
import com.example.pizz_apk.models.RetroFitResponse;
import com.example.pizz_apk.models.User;
import com.example.pizz_apk.services.Utils;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MonCompteFragment extends Fragment {
    private Retrofit retrofit;
    RetroFitRequests requests;
    public int userID;
    public String valueUsername;
    public String valuePassword;
    public String valueEmail;
    public String valueAdresse;
    public String valueTelephone;


    public MonCompteFragment() {
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
        return inflater.inflate(R.layout.fragment_mon_compte, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(
                "userId", Context.MODE_PRIVATE);
        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            userID = extras.getInt("userId");
        }
        else {
            Log.d("MonCompteFragment", "bro: " + userID);
        }

        valueUsername = extras.getString("userUsername");
        valueEmail= extras.getString("userEmail");
        valuePassword = extras.getString("userPassword");
        valueAdresse = extras.getString("userAdresse");
        valueTelephone = extras.getString("userTelephone");
        //change hint from edittext
        EditText emailguest = view.findViewById(R.id.input_email_guest);
        emailguest.setHint("current email : " + valueEmail);
        EditText passwordguest = view.findViewById(R.id.input_password_guest);
        passwordguest.setHint("current password : " + valuePassword);
        EditText adressguest = view.findViewById(R.id.input_adresse_guest);
        adressguest.setHint("current adresse : " + valueAdresse);
        EditText phoneguest = view.findViewById(R.id.input_telephone_guest);
        phoneguest.setHint("current phone : " + valueTelephone);
        EditText usernameguest = view.findViewById(R.id.input_username_guest);
        usernameguest.setHint("current username : " + valueUsername);
        Button editButton = view.findViewById(R.id.btn_modif_account);

        editButton.setOnClickListener(v -> {
            HandleUpdateUser();
            Log.d("HandleUpdateUser", "HandleUpdateUser called");
            Toast.makeText(requireContext(), "Votre compte a été modifié", Toast.LENGTH_SHORT).show();
        });
    }

    public void HandleUpdateUser() {
        HashMap<String, String> map = new HashMap<>();
        map.put("username", "test");
        map.put("email", "");
        HashMap<String, String> mapadrss = new HashMap<>();
        mapadrss.put("addresse", "");
        map.put("password", "");
        map.put("telephone", "");

        Call<RetroFitResponse<User>> call = requests.getUserById(this.userID);

        //get current login user current id
        call.enqueue(new Callback<RetroFitResponse<User>>() {
            @Override
            public void onResponse(Call<RetroFitResponse<User>> call, Response<RetroFitResponse<User>> response) {
                if (response.isSuccessful()) {
                    RetroFitResponse<User> retroFitResponse = response.body();
                    ArrayList<User> users = retroFitResponse.getResults();
                    User user = users.get(0);
                    int id = user.getId();
                    System.out.println("id : " + id);
                    requests.executeUpdate(map).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(requireContext(), "Modification effectuée", Toast.LENGTH_SHORT).show();
                                System.out.println("update success");
                            } else if (response.code() == 400) {
                                Log.d("TAG", "onResponse: " + response.errorBody());
                                Toast.makeText(requireContext(), "Modification échouée", Toast.LENGTH_SHORT).show();
                                System.out.println("update failed");
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Log.d("TAG", "onFailure: " + t.getMessage());
                            Toast.makeText(requireContext(), "Modification échouée", Toast.LENGTH_SHORT).show();
                            System.out.println("update failed");
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<RetroFitResponse<User>> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getMessage());
                Toast.makeText(requireContext(), "Modification échouée", Toast.LENGTH_SHORT).show();
                System.out.println("update failed");
            }
        });
    }
}